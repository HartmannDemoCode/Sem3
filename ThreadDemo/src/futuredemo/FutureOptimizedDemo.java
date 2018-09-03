/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package futuredemo;

/**
 *
 * @author tha
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Java program to show how to use Future in Java. Future allows to write
 * asynchronous code in Java, where Future promises result to be available in
 * future
 */
public class FutureOptimizedDemo {

    private static final ExecutorService threadpool = Executors.newFixedThreadPool(3);

    public static void main(String args[]) {

        List<Future<Long>> futures = new ArrayList();
        for (int i = 0; i < 10; i++) {
            int val = new Random().nextInt(50);
            Future<Long> fut = threadpool.submit(new FactorialCalculator(val));
            futures.add(fut);
        }

        Iterator<Future<Long>> it = futures.iterator();
        int count = 0;
        int reruns = 0;
        while (futures.size() > 0) {
            count++;
            Future<Long> fut = it.next();
            try {
                if (fut.isDone()) {
                    System.out.println("Result is: " + fut.get() + " in rerun no: " + reruns);
                    it.remove();

                } else {
                    Thread.sleep(1);
                }
                if (!it.hasNext()) {
                    reruns++;
                    it = futures.iterator(); //restart the iterator to point to first elemment
                }
            } catch (InterruptedException | ExecutionException ex) {
                System.out.println(ex.getMessage());
            }
        }
    

            threadpool.shutdown();
            System.out.printf("FIINISHED after %s runs and %s reruns", count, reruns);
            System.out.println("");
            System.out.println("");
            System.out.println("Another approach using a ExecutorCompletionService........................................");
            ExecutorService executor = Executors.newFixedThreadPool(4);
            CompletionService<Long> completionService = new ExecutorCompletionService<Long>(executor);

            for (int i = 0; i < 10; i++) {
                int val = new Random().nextInt(50) - 10; //sometimes negative to throw an IllegalArgumentException.
                completionService.submit(new FactorialCalculator(val));
            }

            int received = 0;
            boolean errors = false;

            while (received <= 10 && !errors) {
                try {
                    Future<Long> resultFuture = completionService.take(); //blocks if none available
                    Long result = resultFuture.get();
                    received++;
                    System.out.println("Result from completionservice: " + result);

                } catch (InterruptedException | ExecutionException e) {
                    errors = true;
                }
            }
            Set<Thread> threadSet = Thread.getAllStackTraces().keySet(); //get list of running threads
            //Thread[] threadArray = threadSet.toArray(new Thread[threadSet.size()]);
            threadSet.forEach((t)->{System.out.println(t);});
            executor.shutdown();

        }
    

    private static class FactorialCalculator implements Callable<Long> { // Callable interfacet has one method: E call() that returns an object of type E

        private final int number;

        public FactorialCalculator(int number) {
            this.number = number;
        }

        @Override
        public Long call() {
            long output = 0;
            try {
                output = factorial(number);
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
            return output;
        }

        private long factorial(int number) throws InterruptedException {
            if (number < 0) {
                throw new IllegalArgumentException("Number must be greater than zero");
            }
            long result = 1;
            while (number > 0) {
                Thread.sleep(1); // adding delay for the sake of example. 
                result = result * number;
                number--;
            }
            return result;
        }
    }
}

// http://javarevisited.blogspot.com/2015/01/how-to-use-future-and-futuretask-in-Java.html#ixzz3kbjdUqjk
