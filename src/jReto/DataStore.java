package jReto;

import java.io.*;
import java.util.Scanner;

public class DataStore {

    private static final String PATH = "F:\\My Work\\FCI\\Third Year\\Second Term\\SW-2\\LetsChat\\chat.txt";
    private WriteGUI writeGUI;

    public DataStore(WriteGUI writeGUI){
        this.writeGUI = writeGUI;
    }

    public void WriteToFile(String msg) throws IOException {
        FileWriter file = new FileWriter(PATH);
        file.write(msg + "\n");
      //  file.close();
        ReadFromFile();
    }

    public void ReadFromFile() throws IOException{
        String chat = "";
        Scanner file = null;
        try{
             file = new Scanner(PATH);
            while(file.hasNextLine()){
                chat += file.nextLine();
            }
        }finally {
            file.close();
        }
        writeGUI.updateChatData(chat);
        //return chat;
    }
}
