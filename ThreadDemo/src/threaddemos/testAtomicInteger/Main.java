/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threaddemos.testAtomicInteger;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author tha
 */
public class Main {
    private static AtomicInteger at = new AtomicInteger(0); //Thread safe counter
    private static int count = 0;                           //a normal counter that is not synchronized.
    static class MyRunnable implements Runnable{

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                at.addAndGet(1);
                count++;
            }
        }
    }
    public static void main(String[] args) {
        try {
            Thread t1 = new Thread(new MyRunnable());
            Thread t2 = new Thread(new MyRunnable());
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            System.out.println("Atomic: "+at.toString()); //This will allways be 2*10000
            System.out.println("Count: "+count); //This will often be less
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
