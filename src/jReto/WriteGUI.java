package jReto;

public interface WriteGUI {
    //This interface serve like observer pattern to update GUI
    void setDisplayName(ChatRoom displayName);
    void updateChatData(String txt);
    void WriteToChat(String msg, MainGUI mainGUI);
    void removeChatPeer(ChatRoom chatPeer);
}
