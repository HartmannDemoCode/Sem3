/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threaddemos.waitAndNorify;

/**
 *
 * @author tha
 * Purpose: to test concurrency with two threads using wait() and notify()
 */
public class Main {
    public static void main(String[] args) {
        Processor p = new Processor();
    new Thread(new Runnable(){
        @Override
        public void run() {
            try {
                p.consume();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }).start();
        new Thread(new Runnable(){
        @Override
        public void run() {
            try {
                p.produce();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }).start();
        
    }
}
