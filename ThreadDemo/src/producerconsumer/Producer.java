package producerconsumer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Producer implements Runnable {
  MessageHolder msgHolder;
  int elementsToProduce = 5;
  
  public Producer(MessageHolder msgHolder,int elements) {
    this.msgHolder = msgHolder;
    elementsToProduce = elements;
  }
  @Override
  public void run() {
    for (int i = 0; i < elementsToProduce; ++i) {
      try {
        Thread.sleep(100); //Simulate time it takes to produce an item
        String msg = "Produced by: "+Thread.currentThread().getName()+"--"+ System.nanoTime();
        msgHolder.putMessage(msg);
      } catch (InterruptedException ex) {
        Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }
}
