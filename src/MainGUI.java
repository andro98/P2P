import javax.swing.*;
import java.awt.*;

public class MainGUI extends Frame {

    private Button connectBtn;
    private TextField ip;
    private TextField port;
    private TextArea chat;
    private TextField message;
    private Button sendBtn;

    private void Init(){
        connectBtn = new Button("Connect");
        connectBtn.setBounds(400,50, 100,30);

        ip = new TextField();
        ip.setText("localhost");
        ip.setBounds(50,50,300,30);

        chat = new TextArea();
        chat.setBounds(50, 100, 600, 400 );

        message = new TextField();
        message.setBounds(50, 550, 400, 30);

        sendBtn = new Button("Send");
        sendBtn.setBounds(500,550,100,30);

        add(connectBtn);
        add(ip);
        add(chat);
        add(message);
        add(sendBtn);
    }

    public MainGUI(){
        setSize(800,600);
        setLayout(null);
        setVisible(true);
        setTitle("Let's Chat");
        setBackground(new Color(0x8B81A6FF, true));

        Init();
    }

    public static void main(String[] args){
        MainGUI mainGUI = new MainGUI();

    }
}
