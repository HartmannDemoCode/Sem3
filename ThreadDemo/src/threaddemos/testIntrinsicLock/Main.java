package threaddemos.testIntrinsicLock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author tha
 */
public class Main {

    List<Integer> list1 = new ArrayList();
    List<Integer> list2 = new ArrayList();
    Object lock1 = new Object();
    Object lock2 = new Object();
    
    public static void main(String[] args) {
        Main main = new Main();
        main.work();
    }
    public void work() {
        try {
            long start = System.currentTimeMillis();
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    process();
                }
            });
            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    process();
                }
            });
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            long end = System.currentTimeMillis();
            System.out.printf("The program was running for %s milliseconds\n", end - start);
            System.out.printf("List1 has %s number of items. List2 has %s number of items\n", list1.size(), list2.size());
        } catch (InterruptedException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void process() {
            //SOLUTION: to create to new objects and use their intrinsic locks to make atomic use of each individual method (without blocking all other methods in the class)
        Random ran = new Random();
        for (int i = 0; i < 1000; i++) {
            firstProcess(ran);
            secondProcess(ran);
        }
    }

    //public synchronized void firstProcess(Random ran) { //This will sync the method so that only one thread can use it at a time. It doubles the runtime.
    //PROBLEM:  synchronizing the method is that the intrinsic lock that is passed to the caller thread locks the whole object (thereby locking use of method: secondProcess() as well.
    //public synchronized void firstProcess(){
    public void firstProcess(Random ran) {
        synchronized (lock1){
            try {
                Thread.sleep(1);
                list1.add(ran.nextInt(100));
            } catch (InterruptedException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

    public void secondProcess(Random ran) {
        synchronized (lock2) {
            try {
                Thread.sleep(1);
                list2.add(ran.nextInt(100));

            } catch (InterruptedException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

}
