/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threaddemos.testProducerConsumerPattern;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


/**
 *
 * @author tha
 * Purpose using the concurrent BlockingQueue to show how to implement the Producer-Consumer pattern
 * 2 Threads are set up. On is filling the queue, the other is taking elements of the queue.
 * The BlockingQueue is Threadsafe and very friendly in that it fills up the queue and patiently waits til the queue has more space before it fils more elements in.
 * In the same way the take method takes elements of the queue only when there are elements to take and patiently waits till there are more elements to take.
 */
public class Main {
    BlockingQueue<Integer> queue = new ArrayBlockingQueue(10); //queue (FIFO) like an arraylist with room for 10 elements
    Random random = new Random();
    public static void main(String[] args){
        Main main = new Main();
        new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    main.producer();
                } catch (InterruptedException ex) {ex.printStackTrace();}
            }
        }).start();
        new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    main.consumer();
                } catch (InterruptedException ex) {ex.printStackTrace();}
            }
        }).start();
        
    }
    public void producer() throws InterruptedException{
        while(true){
            queue.put(random.nextInt(100));
            System.out.println("put");
        }
    }
    public void consumer() throws InterruptedException{
        int number;
       while(true) {
           if(random.nextInt(10) == 0){ //on average grap a number from the queue 1 of 10 times
               System.out.println("take");
              number = queue.take();
               System.out.printf("An element with value: %s was taken from the queue of size: %s\n", number, queue.size());
           }
           Thread.sleep(100);
        
        }
    }
}
