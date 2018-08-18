package threaddemos.testInterrupted;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tha
 */
public class TheRunnable implements Runnable {

    @Override
    public void run() {
        int iterations = 5;
        try {
            for (int i = 1; i <= iterations; i++) {
                Thread.sleep(1000);
                System.out.format("%s. iteration in TheRunnable Thread\n", i);
            }
        } catch (InterruptedException ex) {
            System.err.printf("%s from the run method in TheRunnable class\n", ex.getMessage());
        }
    }
}
