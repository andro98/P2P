package jReto;

import java.util.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.io.*;

public class FakeDataGenerator extends Thread {

    private List<String> fakeData;
    private static final String PATH = "F:\\My Work\\FCI\\Third Year\\Second Term\\SW-2\\LetsChat\\data.txt";
    private static final String PATH2 = "/home/george/P2P chat/P2P/data.txt";
    public String data;
    private WriteGUI generator;
    private MainGUI mainGUI;

    public FakeDataGenerator (MainGUI mainGUI) throws IOException{
        fakeData = Collections.emptyList();
        fakeData = Files.readAllLines(Paths.get(PATH2), StandardCharsets.UTF_8);

        this.mainGUI = mainGUI;
    }

    @Override
    public void run() {
        boolean flag = true;

        try {
            while(flag){
                if(fakeData.size() > 0){
                    int rand = (new Random().nextInt(fakeData.size()));
                    data = fakeData.get(rand);

                    mainGUI.WriteToChat(data, mainGUI);

                    Thread.sleep(4000);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
    }
}
