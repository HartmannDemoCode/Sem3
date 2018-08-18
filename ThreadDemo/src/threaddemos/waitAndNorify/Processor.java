/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threaddemos.waitAndNorify;

import java.util.Scanner;

/**
 *
 * @author tha 2 methods: produce and consume will be run in 2 different threads
 * produce(): starts out (since consume begins by sleeping 2000 millis.
 * produce(): synchronises by using (this: the Processor object)s intrinsic
 * lock. wait() is called to transfere control of the lock to the next thread
 * that needs it in the other thread the notify() method is used to tell the
 * first thread to wake up (notify() does not free the lock. That happens when
 * the synchronized code block is finished.
 *
 */
public class Processor {

    public void produce() throws InterruptedException {
        synchronized (this) {
            System.out.println("Starting the produce method");
            wait(); //wait() will put this synchronized block to sleep where it will await a notify() call before it can again presume (as soon as the lock is released that is)
            System.out.println("produce() was resumed");
        }
    }

    public void consume() throws InterruptedException {
        Thread.sleep(2000);
        Scanner scan = new Scanner(System.in);
        synchronized (this) {
            System.out.println("starting the consume() method");
            System.out.println("Press enter to continue...");
            scan.nextLine();
            notify(); //This will wake the other thread up so it is ready to work when the lock is released.
            System.out.println("notify() was run");
        }
    }
}
