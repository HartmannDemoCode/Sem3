/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import entity.Customer;
import exceptions.CustomerNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.eclipse.persistence.tools.schemaframework.PopulationManager;

/**
 *
 * @author thomas
 */
public class CustomerFacade {

    private final static Map<Integer, Customer> customers = new HashMap();

    static {
        resetData();
    }

    public List<Customer> getAllCustomers() {
        return new ArrayList(customers.values());
    }

    public Customer getCustomerById(int id) {
        return customers.get(id);
    }

    public Customer createCustomer(Customer c) {
        
        int maxval = Collections.max(customers.entrySet(), (entry1, entry2) -> entry1.getKey() - entry2.getKey()).getKey();
        customers.put(maxval, c);
        c.setId(maxval);
        return c;
    }
    public Customer changeCustomer(Customer c){
        customers.put(c.getId(), c);
        return c;
    }
    public Customer deleteCustomer(int id) throws CustomerNotFoundException{
        Customer c = customers.remove(id);
        if(c == null)
            throw new CustomerNotFoundException("No customer found with id: "+id);
        return c;
    }
    
    public Map<Integer, Customer> getData(){
        return customers;
    }
    
    public static void resetData(){
        customers.clear();
        customers.put(1, new Customer(1, "Oluf Palme", 96));
        customers.put(2, new Customer(2, "Sarah Palin", 56));
        customers.put(3, new Customer(3, "Vladimir Putin", 12));
    }
}
