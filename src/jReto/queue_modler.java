package jReto;
import java.util.LinkedList;
import java.util.List;
import java.io.*;
public class queue_modler {
    private List queue = new LinkedList();
    private int limit = 30;

    public queue_modler(int limit) {
        this.limit=limit;
    }
    public synchronized void enqueue(FakeDataGenerator item) throws InterruptedException  {
        while(this.queue.size() == this.limit) {

            appendStrToFile("F:\\My Work\\FCI\\Third Year\\Second Term\\SW-2\\LetsChat\\data.txt", String.valueOf(item.data));
            this.queue.remove(0);
            item.wait(4);
        }
        if(this.queue.size() == 0) {
            item.run();
        }
        this.queue.add(item.data);
    }
    public synchronized Object dequeue() throws InterruptedException{
        while(this.queue.size() == 0){
            wait();
        }
        if(this.queue.size() == this.limit){
            notifyAll();
        }

        return this.queue.remove(0);
    }
    public static void appendStrToFile(String fileName,
                                       String str)
    {
        try {

            // Open given file in append mode.
            BufferedWriter out = new BufferedWriter(
                    new FileWriter(fileName, true));
            out.write(str);
            out.close();
        }
        catch (IOException e) {
            System.out.println("exception occoured" + e);
        }
    }

}
