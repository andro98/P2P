package jReto;

import de.tum.in.www1.jReto.LocalPeer;
import de.tum.in.www1.jReto.RemotePeer;
import de.tum.in.www1.jReto.module.wlan.WlanModule;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LocalChatPeer {

	private MainGUI mainGUI;
	private LocalPeer localPeer;
	private Map<RemotePeer, ChatRoom> chatPeers = new HashMap<>();

	private String displayName;
		
	public LocalChatPeer(MainGUI mainGUI) {
		this.mainGUI = mainGUI;
		
		try {
			this.initializeLocalPeer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void initializeLocalPeer() throws IOException {

		WlanModule wlanModule = new WlanModule("P2P");
		this.localPeer = new LocalPeer(Arrays.asList(wlanModule), Executors.newSingleThreadExecutor());
	}

	public void start(String displayName) {
		this.displayName = displayName;
		
		this.localPeer.start(
			peer -> createChatPeer(peer), 
			peer -> removeChatPeer(peer)
		);
	}
	
	public void createChatPeer(RemotePeer peer) {
		if (this.chatPeers.get(peer) != null) {
			System.err.println("We already have a chat peer for this peer!");
			return;
		}
		
		ChatRoom chatPeer = new ChatRoom(peer, this.displayName, this.mainGUI);
		this.chatPeers.put(peer, chatPeer);

	}
	public void removeChatPeer(RemotePeer peer) {
		this.mainGUI.removeChatPeer(this.chatPeers.get(peer));
		this.chatPeers.remove(peer);
	}
}
