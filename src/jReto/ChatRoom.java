package jReto;

import de.tum.in.www1.jReto.Connection;
import de.tum.in.www1.jReto.RemotePeer;
import de.tum.in.www1.jReto.connectivity.InTransfer;
import de.tum.in.www1.jReto.connectivity.OutTransfer;
import sun.applet.Main;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChatRoom {
	
    /** The display name of the local peer in the chat */
	private String localDisplayName;
    /** The display name of the remote peer in the chat */
	private String remoteDisplayName;
    /** The full text in the chat room; contains all messages. */
	private static String chatText = "";

	private WriteGUI writeGUI;

    /** The remotePeer object representing the other peer in the chat room (besides the local peer) */
	private RemotePeer remotePeer;
	/** The Connection used to receive chat messages. */
	private Connection incomingConnection;
    /** The Connection used to send chat messages. */
	private Connection outgoingConnection;
	
	public ChatRoom(RemotePeer remotePeer, String localDisplayName, MainGUI mainGUI) {
		this.writeGUI = mainGUI;

		this.localDisplayName = localDisplayName;
		this.remotePeer = remotePeer;
		
        // When an incoming connection is available, call acceptConnection.
		this.remotePeer.setIncomingConnectionHandler((peer, connection) -> this.acceptIncomingConnection(connection));
		
        // Create a connection to the remote peer
		this.outgoingConnection = remotePeer.connect();
        // The first message sent through the outgoing connection contains the display name that should be used, so it is sent here.
		this.outgoingConnection.send(new TextMessage(localDisplayName).serialize());
	}
	
	private void acceptIncomingConnection(Connection connection) {
		if (this.incomingConnection == null) {
            // If this is the first connection, we use it to receive message data. Therefore we call handleMessageData when data was received.
			this.incomingConnection = connection;
			this.incomingConnection.setOnData((t, data) -> handleMessageData(data));
		}
	}
	public void sendMessage(String message, int size, int cur) {
		this.outgoingConnection.send(new TextMessage(message).serialize());

		if(cur == size - 1){
			appendChatMessage(this.localDisplayName, message);
		}

	}
	private void handleMessageData(ByteBuffer data) {	
		String message = new TextMessage(data).text;
		
		if (this.remoteDisplayName == null) {
			this.setDisplayName(message);
		} else {
			appendChatMessage(this.remoteDisplayName, message);
		}
	}
	private void appendChatMessage(String displayName, String message) {
		chatText += displayName+": "+ message+"\n";

		writeGUI.updateChatData(chatText);
	}

	private void setDisplayName(String displayName) {
		this.remoteDisplayName = displayName;
		writeGUI.setDisplayName(this);
	}
	public String getDisplayName() {
		return this.remoteDisplayName;
	}

}