/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package futuredemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author tha
 * Purpose: Show how to use Future and interface: Callable from java.util.concurrent
 * Callable is a sublement to the Runnable interface
 * Callable can only by used in a ThreadPool (not like Runnable in new Thread(new Runnable...))
 * Callable has one method: call that returns a value (not like Runnable from where the run() method is returning void
 * Callable can throw checked exceptions if nothing can be returned
 * 
 * The return value is collected in a Future<T> object from where it can be extracted with the futures get() method (this method will await the Callable threads execution - and is therefore a blocking call).
 * The same result could be achieved with a Runnable object with a getter method and a thread.join to ensure that the thread had finished its work.
 */
public class Ex4FutureCollectionDemo {
    
    private final static List<Future<Long>> futures = new ArrayList();
    private final static ExecutorService threadPool = Executors.newFixedThreadPool(4); //4 threads will be running (+ the main thread)
    
    private final static String sentence = "Lorem ipsum dolor sit amet, ei vix case euripidis, eam at homero commune reprehendunt. Ea pro adhuc regione, per in possit apeirian assentior. Ex purto quando pri, ei sea veri adhuc, aperiri vocibus suscipiantur et duo. Id quis vitae diceret sit, ne quo probo elitr tempor. Exerci officiis adversarium an vis. Has solum laudem et.";
    
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        String[] tokens = sentence.split(" ");
        for (String token : tokens) {
            Future fut = threadPool.submit(new WordLengthCalc(token)); // A new task is submittet to the Threaspool for each word. A future result is returned and put in a list.
            futures.add(fut);
        }
        System.out.println("Now all tasks are submittet");
        Long sum = 0L;
        for (Future<Long> future : futures) {
            sum += future.get(); //get() metoden på en future er et blokkerende kald der afventer afslutningen af call metoden i den Callable der blev submittet til thread poolen.
        }
        System.out.printf("Now all tasks have completed. The sum is %s%n", sum);
        threadPool.shutdown(); //Without this the jvm will continue to run.
    }
}

class WordLengthCalc implements Callable<Long>{
    private String word;
    public WordLengthCalc(String word){ //To get data into the call method it must go through the constructor (since call() cant be changed when implementing Callable interfacet
        this.word = word;
    }
    @Override
    public Long call() throws Exception {
        Thread.sleep(1000);
        return new Long(word.length());
    }
}