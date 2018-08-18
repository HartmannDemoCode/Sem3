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
public class Target {
    public void call(int threadNo){
        System.out.format("The target was called from thread no %s\n", threadNo);
    }
}
