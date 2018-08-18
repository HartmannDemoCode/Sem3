/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threaddemos.testDeadLock;

/**
 *
 * @author tha
 */
public class Account {
    private int balance = 10000;
    public int getBalance(){
        return balance;
    }
    public void withdraw(int amount){
        balance -= amount;
    }
    public void deposit(int amount){
        balance += amount;
    }
    public static void transfer(Account a1, Account a2, int amount){
        a1.deposit(amount);
        a2.withdraw(amount);
    }
}
