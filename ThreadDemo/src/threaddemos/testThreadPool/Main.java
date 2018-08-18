/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threaddemos.testThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author tha
 * Purpose: Show how a thread pool is stated up with Executors.newFixedThreadPool(2); 2 threads in the pool in this case
 * The ExecutorService then uses the submit() method to register tasks to be done (as objects of type Runnable)
 * in this case 5 tasks are registered and the thread pool allocates tasks - that will be executed as soon as an existing thread is freed up.
 */
public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    public void run() {
        //2 Threads and 5 tasks/runnable objects
        //
        try {
            ExecutorService exe = Executors.newFixedThreadPool(2); //By reusing the threads we save the overhead involved in starting up threads.
            for (int i = 1; i <= 5; i++) {
                exe.submit(new Processor(i));
                System.out.println("submitted: "+i);
            }
            exe.shutdown();
            System.out.println("All tasks are now submitted");
            exe.awaitTermination(1, TimeUnit.DAYS); // the thread pool will allow a day for all tasks to be completed before terminating any unfinished tasks (none in this case)
            System.out.println("All tasks are now completed");
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
}

class Processor implements Runnable { //printout starting and finishing and wait a second in between

    private int id = 0;
    public Processor(int id){ this.id = id; } 
    @Override
    public void run() {
        System.out.printf("starting number %s\n", id);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.printf("Finishing number %s\n", id);
    }
}
