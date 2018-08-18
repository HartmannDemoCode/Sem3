package producerconsumer;

public class Consumer implements Runnable {
  MessageHolder msgHolder;
  int elementsToConsume = 0;
  
  public Consumer(MessageHolder msgHolder,int elements) {
    this.msgHolder = msgHolder;
    elementsToConsume = elements;
  }
  @Override
  public void run() {
    for (int i = 0; i < elementsToConsume; ++i) {
      System.out.println("Consumer "+Thread.currentThread().getName()+ " GET: " + msgHolder.getMessage());
    }
  }
}
