/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threaddemos.testProducerConsumerPattern.lowLevelVersion;

import java.util.LinkedList;

/**
 *
 * @author tha
 * Purpose: This is to show how to create a producer/consumer pattern with 2 threads and without help from the BlockingQueue object.
 */
public class Main {
    public static void main(String[] args) {
        Processor pro = new Processor();
        new Thread(new Runnable(){

            @Override
            public void run() {
                try { 
                    pro.produce();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable(){

            @Override
            public void run() {
                try { 
                    pro.consume();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }
}
class Processor{
    LinkedList list = new LinkedList(); //This is the shared resource
    Object lock = new Object();
    private final int MAXSIZE = 10;
    private int value = 0;
    
    public void produce() throws InterruptedException{
        while (true) {            
            synchronized(lock){
                while(list.size()==MAXSIZE){
                    lock.wait(); //Avoid busy waiting by using wait() and notify() When the list has reached its maximum allowed size the produce method the waits (gives up its lock on the object
                }
                list.add(value++);
                lock.notify(); //notify returns the lock to a random thread in the waitset (in this case there is only one) - notifyAll() will wake up all threads to go for the lock.
            }
        }
    }
    public void consume() throws InterruptedException{
        while (true) {            
            synchronized(lock){
                while(list.size()==0){
                    lock.wait();
                }
                int element = (int)list.removeFirst();
                System.out.println("Removed "+element);
                Thread.sleep(1000);
                lock.notify();
            }
        }
    }
}
