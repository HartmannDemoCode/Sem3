/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threaddemos;

/**
 *
 * @author tha
 * Self contained Example from: https://docs.oracle.com/javase/tutorial/essential/concurrency/simple.html
 */
public class SimpleThreads {

    public static void main(String args[]) throws InterruptedException {
        // Delay, in milliseconds before we interrupt MessageLoop thread (default one minute).
        long patience = 1000 * 60;

        threadMessage("Starting MessageLoop thread");
        long startTime = System.currentTimeMillis();
        Thread t = new Thread(new MessageLoop());
        t.start();

        threadMessage("Waiting for MessageLoop thread to finish");
        // loop until MessageLoop  thread exits
        while (t.isAlive()) {
            threadMessage("Still waiting...");
            // Wait maximum of 1 second for MessageLoop thread to finish.
            t.join(1000);
            if (((System.currentTimeMillis() - startTime) > patience)
                  && t.isAlive()) {
                threadMessage("Tired of waiting!");
                t.interrupt(); //Main thread is interrupting thread0
                t.join(0);
            }
        }
        threadMessage("Finally!");
    }
        // Display a message, preceded by
    // the name of the current thread
    static void threadMessage(String message) {
        String threadName = Thread.currentThread().getName();
        System.out.format("%s: %s%n",threadName,message);
    }
    
    private static class MessageLoop implements Runnable {
        public void run() {
            String importantInfo[] = {
                "First sentence in array",
                "Second sentence in array",
                "Third sentence in array",
                "Fourth sentence in array",
                "Fifth sentence in array",
                "Sixth sentence in array",
                "Seventh sentence in array",
                "Eighth sentence in array",
                "Ninth sentence in array",
                "Tenth sentence in array",
                "Eleventh sentence in array",
                "Twelfth sentence in array",
                "Thirteenth sentence in array",
                "Fourteenth sentence in array",
                "Fifteenth sentence in array",
                "Sixteens sentence in array",
                "Seventeenth sentence in array",
                "Eighteenth sentence in array",
                "Ninteenth sentence in array",
                "Twentieth sentence in array",
            };
            try {
                for (int i = 0;i < importantInfo.length; i++) {
                    Thread.sleep(4000);
                    threadMessage(importantInfo[i]);
                }
            } catch (InterruptedException e) {
                threadMessage("I wasn't done!");
            }
        }
    }
}
