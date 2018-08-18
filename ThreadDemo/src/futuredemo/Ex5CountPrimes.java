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
 * @author sofus
 */
public class Ex5CountPrimes {

    /**
     * This method calculates if a number is a prime number or not.
     */
    private static boolean isPrime(int n) {
        int k = 2;
        while (k * k <= n && n % k != 0) {
            k++;
        }
        return n >= 2 && k * k > n;
    }

    /**
     *
     * @param start from what number are we starting counting primes?
     * @param range how many numbers onwards are we going to test primes on?
     * @param threadCount how many threads do we have avaliable?
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public static void testPrimes(int start, int range, int threadCount) throws InterruptedException, ExecutionException {
        //The list that will have all the responses from the executor
        List<Future<Boolean>> list = new ArrayList<>();

        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        for (int i = start; i < start + range; i++) {
            //This needs to be final in order for the anonymous inner class 
            //to use the variable
            final int possiblePrimeNumber = i;
            
            Callable<Boolean> task = new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return isPrime(possiblePrimeNumber);
                }
            };
            
            list.add(executor.submit(task));
        }
        //After insertion of all tasks, we can command the executor to 
        //shutdown after last execution
        executor.shutdown();
        int primes = 0;
        
        for (Future<Boolean> list1 : list) {
            //This "voodoo" is a ternarry operator. If you don't know it, read:
            //http://alvinalexander.com/java/edu/pj/pj010018

            primes += list1.get() ? 1 : 0;
        }
        
        
        System.out.println("Number of primes: " + primes);
    }
    //Static method to show system information
    public static void SystemInfo() {
        System.out.printf("# OS:   %s; %s; %s%n",
                System.getProperty("os.name"),
                System.getProperty("os.version"),
                System.getProperty("os.arch"));
        System.out.printf("# JVM:  %s; %s%n",
                System.getProperty("java.vendor"),
                System.getProperty("java.version"));
        // The processor identifier works only on MS Windows:
        System.out.printf("# CPU:  %s; %d \"cores\"%n",
                System.getenv("PROCESSOR_IDENTIFIER"),
                Runtime.getRuntime().availableProcessors());
        java.util.Date now = new java.util.Date();
        System.out.printf("# Date: %s%n",
                new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(now));
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        SystemInfo();
        
        long startTime = System.nanoTime();
        testPrimes(2_000_000_000, 1_000_000, 4);
        long endTime = System.nanoTime();
        
        //Duration in nano seconds
        long duration = (endTime - startTime);
        //duration in milliseconds
        System.out.println("Duration: " + duration / 1_000_000 + " milliseconds");

    }

}
