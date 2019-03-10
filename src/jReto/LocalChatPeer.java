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

//This class responsible for my peer to be discovered
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

		//Create room for chating
		WlanModule wlanModule = new WlanModule("P2P");
		this.localPeer = new LocalPeer(Arrays.asList(wlanModule), Executors.newSingleThreadExecutor());
	}

	public void start(String displayName) {
		this.displayName = displayName;

		//starting peer we start dih feha 7agten onCreate we onRemove
		this.localPeer.start(
			peer -> createChatPeer(peer), 
			peer -> removeChatPeer(peer)
		);
	}
	
	public void createChatPeer(RemotePeer peer) {
		if (this.chatPeers.get(peer) != null) {
			//law heya already 3mla connect abl keda
			System.err.println("We already have a chat peer for this peer!");
			return;
		}
		// by3ml chat roo, we y7ot feha el peer dih
		ChatRoom chatPeer = new ChatRoom(peer, this.displayName, this.mainGUI);
		this.chatPeers.put(peer, chatPeer);

	}
	public void removeChatPeer(RemotePeer peer) {
		//byshelha mn el room
		this.mainGUI.removeChatPeer(this.chatPeers.get(peer));
		this.chatPeers.remove(peer);
	}
}
