package producerconsumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tester {
   public static void main(String[] args) {
      SingleMessageHolder messageHolder = new SingleMessageHolder();
      ExecutorService executor = Executors.newCachedThreadPool();
      executor.execute(new Producer(messageHolder,20));
      executor.execute(new Consumer(messageHolder,5));
      executor.execute(new Consumer(messageHolder,5));
      executor.execute(new Consumer(messageHolder,5));
      executor.execute(new Consumer(messageHolder,5));
      executor.shutdown();
     try {
       executor.awaitTermination(10, TimeUnit.SECONDS);
     } catch (InterruptedException ex) {
       Logger.getLogger(Tester.class.getName()).log(Level.SEVERE, null, ex);
     }
   }
}