package futuredemo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author tha
 */

public class Ex2MyRunnable implements Runnable {
    private static final List<String> list = new ArrayList();
    private static CountDownLatch latch = new CountDownLatch(100);
    @Override
    public void run() {
        try {
            Thread.sleep(100);
            
        } catch (InterruptedException ex) {
            System.out.println("We have to catch this exception since a Runnable can not throw exceptions");
        }
        //Save the work in a field on the runnable class
        list.add(Thread.currentThread().getName());
        latch.countDown();
    }
     
    public static void main(String args[]) throws InterruptedException{
        //Get ExecutorService from Executors utility class, thread pool size is 3 
        // 3 Threads running means that each second three tasks are solved (call method takes one second)
        
        ExecutorService executor = Executors.newFixedThreadPool(3);

        //Create MyCallable instance
        Runnable runnable = new Ex2MyRunnable();
        
        for(int i=0; i< 100; i++){
            
            executor.submit(runnable);
            
        }
        executor.shutdown();
        // Or use a CountDownLatch
        executor.awaitTermination(1, TimeUnit.DAYS);
       
        latch.await();
        for(String str : list){
            
                //print the return value of Future, notice the output delay in console
                // because Future.get() waits for task to get completed
                System.out.println(new Date()+ "::"+str);
            
        }
        //shut down the executor service now
        //executor.shutdown();
    }
 
}

