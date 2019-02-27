/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import entity.Customer;
import exceptions.CustomerNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author thomas
 */
public class CustomerFacadeTest {
    CustomerFacade instance = new CustomerFacade();
    
    public CustomerFacadeTest() {
    }
    @Before
    public void resetData(){
        CustomerFacade.resetData();
    }

    /**
     * Test of getAllCustomers method, of class CustomerFacade.
     */
    @Test
    public void testGetAllCustomers() {
        System.out.println("getAllCustomers");
        List<Customer> result = instance.getAllCustomers();
        assertEquals(3, result.size());
    }

    /**
     * Test of getCustomerById method, of class CustomerFacade.
     */
    @Test
    public void testGetCustomerById() {
        System.out.println("getCustomerById");
        int id = 0;
        String expResult = "Sarah Palin";
        Customer result = instance.getCustomerById(2);
        assertEquals(expResult, result.getName());
        
    }

    /**
     * Test of createCustomer method, of class CustomerFacade.
     */
    @Test
    public void testCreateCustomer() {
        System.out.println("createCustomer");
        Customer c = new Customer("Freddie Mercury", 36);
        Customer expResult = null;
        Customer result = instance.createCustomer(c);
        assertTrue(result.getId() != 0 && result.getAge() == 36);
    }

    /**
     * Test of changeCustomer method, of class CustomerFacade.
     */
    @Test
    public void testChangeCustomer() {
        System.out.println("changeCustomer");
        Customer c = new Customer(1, "Donald Duck", 103);
        String expResult = "Donald Duck";
        Customer result = instance.changeCustomer(c);
        assertEquals(expResult, result.getName());
        
    }

    /**
     * Test of deleteCustomer method, of class CustomerFacade.
     */
    @Test
    public void testDeleteCustomer() throws CustomerNotFoundException {
        System.out.println("deleteCustomer");
        int id = 0;
        int expResult = 2;
        Customer result = instance.deleteCustomer(2);  
        assertEquals(expResult,instance.getAllCustomers().size());
    }

    /**
     * Test of getData method, of class CustomerFacade.
     */
    @Test
    public void testGetData() {
        System.out.println("getData");
        int expResult = 3;
        Map<Integer, Customer> result = instance.getData();
        assertTrue(result.size()==3);
        
    }
    
}
