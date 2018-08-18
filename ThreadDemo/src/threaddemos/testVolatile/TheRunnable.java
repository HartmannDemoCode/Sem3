/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threaddemos.testVolatile;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tha
 */
public class TheRunnable implements Runnable{
    private volatile boolean running = true; //volatile keyword ensures that the variable can be accessed from outside the thread (without the thread using a "cached" version. (In some systems the check performed by the while loop will not bother to actually check if the value of "running" has changed since nothing inside the run method of the thread it selv could have changed the value.
    @Override
    public void run() {
        try {
            while(running){
                Thread.sleep(100);
                System.out.println("Still running...");
            }
        } catch (InterruptedException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public void shutDown(){
        running = false;
    }
    
}
