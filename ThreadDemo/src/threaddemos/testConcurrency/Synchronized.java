/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threaddemos.testConcurrency;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tha
 * Purpose: Interleave - Create example of interleaving actions in to different threads.
 * Reference: https://www.youtube.com/watch?v=lotAYC3hLVo
 */
public class Synchronized {
    private int counter = 0;
    public static void main(String[] args) {
        Synchronized app = new Synchronized();
        app.doStuff();
    }
    private  void incrementCounter(){
        counter++;
    }
    private synchronized void incrementCounterSynchronized(){
        counter++;
    }
    
    private void doStuff(){
            Thread t1 = new Thread(new Runnable(){
                @Override
                public void run() {
                    for (int i = 0; i < 10000000; i++) {
                        //counter++;  // equals to counter = counter + 1; which is a 3 step proces: 
                                    // 1: read counter. 2: increment counter. 3: Store new value into counter. 
                                    // When this is not syncronized different threads can work on counter simultaneously.
                                    // Thereby both read value of counter, both increment and when storing back - overwrite each others changes.

                        incrementCounterSynchronized(); //This is the solution!! the synchronized method creates an intrinsic lock - which means that all other threads have to wait to use the method untill the current threads has finished using it. Every java object has an intrinsic lock that can be accuired or released by a thread using the object.
                        // it makes all actions in the method ATOMIC
                    }
                }
            });
            Thread t2 = new Thread(new Runnable(){
                @Override
                public void run() {
                    for (int i = 0; i < 10000000; i++) {
                        //counter++;
                        incrementCounter();
                    }
                }
            });
            t1.start();
            t2.start();
        try {
            
            t1.join(); // wait with execution of the main thread (calling thread) untill this thread is finished
            t2.join();
            System.out.printf("counter is now: %s\n", counter);
            
        } catch (InterruptedException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
