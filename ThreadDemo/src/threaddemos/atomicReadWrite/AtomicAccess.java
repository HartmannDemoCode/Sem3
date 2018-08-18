/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threaddemos.atomicReadWrite;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author thomas
 */
public class AtomicAccess implements Runnable {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS");
    private static Map<String, String> configuration = new HashMap<String, String>();

//    public void run() {
//        for (int i = 0; i < 10000; i++) {
//            Map<String, String> currConfig = configuration;
//            String value1 = currConfig.get("key-1");
//            String value2 = currConfig.get("key-2");
//            String value3 = currConfig.get("key-3");
//            if (!(value1.equals(value2) && value2.equals(value3))) {
//                throw new IllegalStateException("Values are not equal.");
//            }
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//        }
//    }
    
    // run method is changed to use the configuation map directly instead of via a new reference.
    public void run() {
        for (int i = 0; i < 10000; i++) {
//            Map<String, String> currConfig = configuration;
            String value1 = configuration.get("key-1");
            String value2 = configuration.get("key-2");
            String value3 = configuration.get("key-3");
            if (!(value1.equals(value2) && value2.equals(value3))) {
                throw new IllegalStateException("Values are not equal.");
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static void readConfig() {
        Map<String, String> newConfig = new HashMap<String, String>();
        Date now = new Date();
        newConfig.put("key-1", sdf.format(now));
        newConfig.put("key-2", sdf.format(now));
        newConfig.put("key-3", sdf.format(now));
        configuration = newConfig;
    }
    
    //changed the method to put values into the static configuration hashMap directly insted of into a new map.
//    public static void readConfig() {
////        Map<String, String> newConfig = new HashMap<String, String>();
//        Date now = new Date();
//        configuration.put("key-1", sdf.format(now));
//        configuration.put("key-2", sdf.format(now));
//        configuration.put("key-3", sdf.format(now));
////        configuration = newConfig;
//    }
//    

    public static void main(String[] args) throws InterruptedException {
        readConfig();
        Thread configThread = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    readConfig();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "configuration-thread");
        configThread.start();
        Thread[] threads = new Thread[5];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new AtomicAccess(), "thread-" + i);
            threads[i].start();
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }
        configThread.join();
        System.out.println("[" + Thread.currentThread().getName() + "] All threads have finished.");
    }
}
