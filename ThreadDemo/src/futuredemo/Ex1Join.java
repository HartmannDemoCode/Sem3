package futuredemo;

/**
 *
 * @author tha
 */
public class Ex1Join{
    private static int result = 0;
    public static void main(String[] args) {
        
            Thread t1 = new Thread(new Runnable() {
                
                @Override
                public void run() {
                    
                    try {
                        Thread.sleep(5000);
                        result += 10;
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            
            Thread t2 = new Thread(new Runnable() {
                
                @Override
                public void run() {
                    try {
                        
                        Thread.sleep(6000);
                        result++;
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            t1.start();
            t2.start();
            System.out.println("Result before join: "+result);
            try{
                t1.join();
                t2.join();
                
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            System.out.println("Result after join: "+result);
    }
}
