/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threaddemos.testInterrupted;

/**
 *
 * @author tha
 */
/* Study the sequence of the output*/
public class MainInterruptedTest {

    public static void main(String[] args) {
        int iterations = 5;
        Thread t = new Thread(new TheRunnable());
        t.start();
        try {
            Thread.sleep(4000);
            t.interrupt();
            System.out.println("We called interrupt on TheRunnable thread");
            for (int i = 0; i < 10; i++) {
                Thread.sleep(100);
                System.out.println("Just stalling...");
            }
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
