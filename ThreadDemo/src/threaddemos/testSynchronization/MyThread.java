/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threaddemos.testSynchronization;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tha
 */
public class MyThread extends Thread {

    private Target target;
    private int threadNo;

    public MyThread(Target target, int threadNo) {
        this.target = target;
        this.threadNo = threadNo;
    }

    @Override
    public void run() {
        synchronized (target) { //Without this line all the instances of MyThread would run more or less at the same time. Now they will wait for each other (2 seconds) before the next thread can access the target object.
            try {
                sleep(2000);
            } catch (InterruptedException ex) {
                System.err.printf("Thread s% was interrupted", threadNo);
            }
            target.call(threadNo);
        }
    }
}
