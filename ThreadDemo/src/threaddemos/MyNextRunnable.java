/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threaddemos;

/**
 *
 * @author tha
 */
public class MyNextRunnable implements Runnable{

    @Override
    public void run(){
        for (int i = 1; i <= 10; i++) {
            System.out.println("Print the "+i+"th number of iteration");
            try {
                Thread.sleep(2000); // Milliseconds.
            } catch (InterruptedException ex) { //This exception is thrown when sleep() is interrupted by another method
                // Interruption is a mechanism whereby a thread that is waiting (or sleeping) can be made to prematurely stop waiting.
                System.out.println("Interrupted exception was caught");
                return; //When the thread is interrupted it should probably not continue to run
                //Thread.currentThread().interrupt(); //causes this thread to...
            }
        }
    }    
}
