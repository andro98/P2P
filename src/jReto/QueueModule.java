package jReto;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class QueueModule {
    private Queue<String> msg;
    private static final int MAX_SIZE = 100;
    private static int CUR_SIZE = 0;
    private DataStore dataStore;

    public QueueModule(DataStore  dataStore){
        msg = new LinkedList<>();
        this.dataStore = dataStore;
    }

    public int addToQueue(String msg){

        if(CUR_SIZE < MAX_SIZE){
            this.msg.add(msg);
            CUR_SIZE++;
            run();
            return 0;
        }else{
            return -1;
        }
    }

    public boolean isFull(){return CUR_SIZE == MAX_SIZE;}

    public void run()  {
            while(CUR_SIZE != 0){
                try{
                dataStore.WriteToFile(msg.remove());
                CUR_SIZE--;}catch (IOException e){
                    e.printStackTrace();
                }
            }
    }
}
