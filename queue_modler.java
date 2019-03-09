package jReto;
import java.util.LinkedList;
import java.util.List;
public class queue_modler {
    private List queue = new LinkedList();
    private int limit = 30;

    public queue_modler(int limit) {
        this.limit=limit;
    }
    public synchronized void enqueue(FakeDataGenerator item) throws InterruptedException  {
        while(this.queue.size() == this.limit) {



        this.queue.remove(0);}
        if(this.queue.size() == 0) {
            notifyAll();
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

}
