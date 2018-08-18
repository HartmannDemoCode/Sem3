package threaddemos.testVolatile;

import java.util.Scanner;

/**
 *
 * @author tha
 */
public class Main {
    public static void main(String[] args) {
        TheRunnable r1 = new TheRunnable();
        Thread t1 = new Thread(r1);
        
        t1.start();
        System.out.println("Press return to stop");
        Scanner scan = new Scanner(System.in);
        scan.nextLine();
        r1.shutDown();
    }
}
