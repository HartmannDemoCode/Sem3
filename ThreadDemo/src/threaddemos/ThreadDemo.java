/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threaddemos;

import sun.audio.AudioPlayer;

/**
 *
 * @author tha
 * ref: https://docs.oracle.com/javase/tutorial/essential/concurrency/runthread.html
 * 
 */
public class ThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        new MyFirstThread().start(); // 2 ways to start a new thread: extend Thread or implement Runnable interface.
        new Thread(new MyFirstRunnable()).start(); // when using and implementation of runnable wrap it in a Thread object. This way is better since the threadobject is free to inherit from any class since it is not a subclass of Thread.
        Runnable r = new MyNextRunnable();
        Thread t = new Thread(r);
        t.start();
        new Thread(new MyFirstRunnable()).start();
        Thread.sleep(4000);
        t.interrupt(); //When you want a task to be ended.
        new Thread(()->{System.out.println("Hello from the lambda");}).start();
    }
}
