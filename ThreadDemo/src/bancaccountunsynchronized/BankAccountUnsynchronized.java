package bancaccountunsynchronized;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// NOT THREADSAFE
public class BankAccountUnsynchronized {

  private double balance;
  private Lock lock = new ReentrantLock();

  public BankAccountUnsynchronized() {
    balance = 0;
  }

  public void deposit(double amount) {
      lock.lock();
      try {
          balance += amount;
      } finally {
          lock.unlock();
      }
   }

  public void withdraw(double amount) {
      lock.lock();
      try {
          balance -= amount;
      } finally {
          lock.unlock();
      }
   }

  public double getBalance() {
    return balance;
  }
}
