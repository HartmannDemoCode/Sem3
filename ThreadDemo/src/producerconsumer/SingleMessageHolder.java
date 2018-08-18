package producerconsumer;


public class SingleMessageHolder implements MessageHolder {
   private String message;
   private boolean hasMessage;
 
   // producer
  @Override
   public synchronized void putMessage(String message) {
      while (hasMessage) { // no room for new message
         try { 
            wait();  // release the lock of this object
         } catch (InterruptedException e) { }
      }
      // acquire the lock and continue
      hasMessage = true;
      this.message = message;
      notify();
   }
 
   // consumer
  @Override
   public synchronized String getMessage() {
      while (!hasMessage) { // no new message
         try {
            wait();  // release the lock of this object
         } catch (InterruptedException e) { }
      }
      // acquire the lock and continue
      hasMessage = false;
      notify();
      return message;
   }
}