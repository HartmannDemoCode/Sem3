package bancaccountunsynchronized;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BankAppExecutor
{
  private static final int NUMBER_OF_THREADS = 1000;
  
  private static void executeTransactions()
  {
    BankAccountUnsynchronized acc = new BankAccountUnsynchronized();
    ExecutorService executor = Executors.newCachedThreadPool();
    for (int i=0; i<NUMBER_OF_THREADS;i++ ) 
    {
        
            executor.execute(new DepositTask(acc));
            executor.execute(new WithdrawTask(acc));
        
    }
    executor.shutdown();
    try {
      executor.awaitTermination(10, TimeUnit.SECONDS);
    } catch (InterruptedException ex) {
      Logger.getLogger(BankAppExecutor.class.getName()).log(Level.SEVERE, null, ex);
    } 
    String result = acc.getBalance() == 0 ? "OK, " : "FAILED, ";
    System.out.print(result);
    System.out.println("Account value after all transactions: "+acc.getBalance());   
  }
  
  public static void main(String[] args)
  {
    for(int i = 0; i < 10; i++){
      executeTransactions();
    }
  }
  
}
