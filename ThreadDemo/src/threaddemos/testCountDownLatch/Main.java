/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threaddemos.testCountDownLatch;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
/**
 *
 * @author tha
 * Purpose: Show how we can use java.util.concurrent.CountDownLatch to ensure that certain threads has completed before we continue with execution of main thread (or any calling thread).
 */
public class Main {
    
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(5); //For awaiting 5 threads/tasks to complete.
        ExecutorService exe = Executors.newFixedThreadPool(5); // Create a thread pool with 5 potential threads
        for (int i = 0; i < 5; i++) {
            exe.submit(new Processor(latch, i)); // creating the 5 threads
        }
        exe.shutdown();
        try {
            latch.await(); //Causes the current calling thread to wait until the latch has counted down to zero, unless the thread is interrupted.
            System.out.println("Now that all our services are executed. We are up and running and now ready to do business.");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
class Processor implements Runnable{ //prints out the thread number and count down the latch when finished
    private CountDownLatch latch;
    private int id;
    public Processor(CountDownLatch latch, int id){
        this.latch = latch;
        this.id = id;
    }
    @Override
    public void run() {
        try {
            //Emulate some time consuming activity - like requesting data from a webservice, database etc.
            Thread.sleep(1000);
            System.out.println("Executing a task here. Task no: "+id);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        latch.countDown(); //When this thread has done its job it counts down the latch before finishing.
    }
    
}