/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threaddemos;

/**
 *
 * @author thomas
 */
public class Demo {
    public static void main(String[] args) throws InterruptedException {
        boolean isRunning = true;
        int counter = 0;
//        Runnable r = new Runnable() {
//            @Override
//            public void run() {
//                while(true){
//                    System.out.println("working...");
//                }
////                System.out.println("From inside the thread");
//            }
//        };
        MyTask mt = new MyTask(counter);
        Thread t1 = new Thread(mt, "Thread1");
//        Thread t2 = new Thread(new MyTask(counter, isRunning), "Thread2");
        t1.start();
//        t2.start();
        Thread.sleep(2000);
        t1.interrupt();
        mt.stop();
        t1.join();
//        t2.join();
        System.out.println("ENDING the program...");
    }
    static class MyTask implements Runnable{
        private boolean shouldRun = true;
        private int counter;
        public MyTask(int counter){
            this.counter = counter;
            
        }
        public void stop(){
            shouldRun = false;
        }
        @Override
        public void run() {
//            for (int i = 0; i < 100; i++) {
//                counter++;
//                System.out.println("Thread: "+Thread.currentThread().getName()+", counter: "+ counter);
//            }
            while(shouldRun){
                counter++;
                System.out.println("working..."+counter);
            }
        }
    }
}
