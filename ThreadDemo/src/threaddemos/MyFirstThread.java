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
public class MyFirstThread extends Thread{
    @Override
    public void run(){
        System.out.println("Kører tråden fra Thread subklassen");
    }
    
}
