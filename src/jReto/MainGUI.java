package jReto;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainGUI extends Frame implements WriteGUI {
    private LocalChatPeer chatPeer;

    private Button connectBtn;
    private TextField ChatRoom;
    private TextArea chat;
    private TextField message;
    private Button sendBtn;
    private List chatPeerList;

    private ChatRoom selectedPeer;
    private ArrayList<ChatRoom> chatPeers;
    private static QueueModule queueModule;
    private static DataStore dataStore;

    public QueueModule getQueueModule(){return  queueModule;}

   /*private static FakeDataGenerator fakeDataGenerator;
    private static Network network;*/

    private void Init(){

        connectBtn = new Button("Connect");
        connectBtn.setBounds(400,50, 100,30);

        ChatRoom = new TextField();
        ChatRoom.setText("Enter Display Name");
        ChatRoom.setBounds(50,50,300,30);

        chat = new TextArea();
        chat.setBounds(50, 100, 500, 400 );
        chat.setEditable(false);

        chatPeerList = new List();
        chatPeerList.setBounds(580,100,150,400);

        message = new TextField();
        message.setBounds(50, 550, 400, 30);

        sendBtn = new Button("Send");
        sendBtn.setBounds(500,550,100,30);

        add(connectBtn);
        add(ChatRoom);
        add(chat);
        add(message);
        add(sendBtn);
        add(chatPeerList);

        chatPeer = new LocalChatPeer(this);
        chatPeers = new ArrayList<>();
    }

    public MainGUI(){
        setSize(800,600);
        setLayout(null);
        setVisible(true);
        setTitle("Let's Chat");
        setBackground(new Color(0x8B81A6FF));

        Init();
    }


    public static void main(String[] args) throws Exception{
        MainGUI mainGUI = new MainGUI();

        FakeDataGenerator fakeDataGenerator = new FakeDataGenerator(mainGUI);
        fakeDataGenerator.start();

        mainGUI.connectBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainGUI.startLocalPeer();
            }
        });

        mainGUI.sendBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String msg = mainGUI.message.getText();
                for(int i =0;i<mainGUI.chatPeers.size(); i++){
                    mainGUI.chatPeers.get(i).sendMessage(msg, mainGUI.chatPeers.size(), i);
                }
            }
        });
    }

    public void startLocalPeer() {
        chatPeer.start(this.ChatRoom.getText());
    }

    @Override
    public void removeChatPeer(ChatRoom chatPeer) {
        this.chatPeers.remove(chatPeer);
        this.chatPeerList.removeAll();

        for (ChatRoom peer : this.chatPeers) {
            this.chatPeerList.add(peer.getDisplayName());
        }
    }

    @Override
    public void updateChatData(String txt) {
        this.chat.setText(txt);
    }

    @Override
    public void WriteToChat(String msg, MainGUI mainGUI) {
        for(int i =0;i<mainGUI.chatPeers.size(); i++){
            mainGUI.chatPeers.get(i).sendMessage(msg, mainGUI.chatPeers.size(), i);
        }
    }

    @Override
    public void setDisplayName(jReto.ChatRoom displayName) {
        this.chatPeers.add(displayName);
        this.chatPeerList.add(displayName.getDisplayName());
    }
}
