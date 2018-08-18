/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threaddemos.testDeadLock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author tha 
 * Purpose: to show how a deadlock can occur. 
 * First we have a
 * concurrency problem when the 2 threads are not locking on any resources:
 * total amount in finalProcess() is not necessaryly 20.000,- Solution to this
 * could be nested synchronize blocks where outer block, locks on account1 and
 * inner block locks on account2 - but its a little hard to read 
 * Other solution:
 * use java.util.concurrent.locks.ReentrantLock. The problem with deadlock comes
 * if you lock your resources in different order in the dirrent threads: If
 * thread1 accuires lock1 and goes on to get lock2, while thread2 has accuired
 * lock2 in order to then get lock 1 - we might have a deadlock situation where
 * no process will release its accuired lock while it is waiting for another. 
 * 2 solutions: 1. Always accuire the locks in the same order (both with
 * reentrantlocks and with nested synchronize code blocks) 
 * 2. solution: create a method that can accuire locks in any order (using the Lock interface tryLock() method)
 */
public class Processor {

    private final Account a1;
    private final Account a2;
    private final Random random;
    private final Lock lock1; //With lock you must lock and unlock from the same thread.
    private final Lock lock2;
    
    public Processor() {
        this.lock2 = new ReentrantLock();
        this.lock1 = new ReentrantLock();
        this.random = new Random();
        this.a2 = new Account();
        this.a1 = new Account();
    }
    public static void main(String[] args) {

        Processor processor = new Processor();

        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                processor.process1();
            }
        });
        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                processor.process2();
            }
        });
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        processor.finalProcess();
    }
    //Solution to not accuire a lock unless both locks can be accuired.
    //run this method in a while loop as long as it is returning false
    private boolean getLocks(Lock lock1, Lock lock2) throws InterruptedException{
        boolean gotLock1 = false;
        boolean gotLock2 = false;
        try{
            gotLock1 = lock1.tryLock();
            gotLock2 = lock2.tryLock();
        }finally{
            if(gotLock1 && gotLock2)
                return true; //That is what we wanted: both locks accuired.
            if(gotLock1 && !gotLock2)
                lock1.unlock();
            if(gotLock2 && !gotLock1)
                lock2.unlock();
        }
        Thread.sleep(1); //wait a bit till maybe the lock will be available.
        return false;
    }


    public void process1() {
        lock1.lock();
        lock2.lock();
        
        try {
            for (int i = 0; i < 10000; i++) {
                Account.transfer(a1, a2, random.nextInt(100)); //moving random amounts from one account to the other 10.000 times
            }
        } finally {
            lock1.unlock();
            lock2.unlock();
        }
    }

    public void process2() {
        lock1.lock();
        lock2.lock();
        try {
            for (int i = 0; i < 10000; i++) {
                Account.transfer(a2, a1, random.nextInt(100)); //moving random amounts back in other thread
            }
        } finally {
            lock1.unlock();
            lock2.unlock();
        }
    }

    public void finalProcess() {
        System.out.println("Account 1 balance: " + a1.getBalance());
        System.out.println("Account 2 balance: " + a2.getBalance());
        //Total amount: This should always be 20.000,- since it is a closed system where 10.000,- in each account is moved around between them. But it will not be 20.000 if we have concurrency problemes.
        System.out.println("Total amount: " + (a1.getBalance() + a2.getBalance()));

    }
}
