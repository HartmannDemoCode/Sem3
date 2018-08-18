/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threaddemos.testSynchronization;

/**
 *
 * @author tha
 */
public class Main {
    /*
    * Denne øvelse går ud på at lade forskellige tråde arbejde med det samme object: Target.
    * Forsøge at gøre det med både syncroniseret adgang og ikke synchroniseret.
    *
    */
    public static void main(String[] args) {
        Target target = new Target();
        MyThread t1 = new MyThread(target, 1);
        MyThread t2 = new MyThread(target, 2);
        MyThread t3 = new MyThread(target, 3);
        MyThread t4 = new MyThread(target, 4);
        MyThread t5 = new MyThread(target, 5);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
