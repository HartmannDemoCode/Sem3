/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author thomas
 */
public class CustomerNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>CustomerNotFoundException</code> without
     * detail message.
     */
    public CustomerNotFoundException() {
    }

    /**
     * Constructs an instance of <code>CustomerNotFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public CustomerNotFoundException(String msg) {
        super(msg);
    }
}
