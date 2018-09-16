/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * if(!city.getCustomerList().contains(this))
//            city.addCustomer(this);
 * @author thomas
 */
public class Tester {
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public static void main(String[] args) {
        City city1 = new City(1, "Aalborg");
        Customer cus1 = new Customer(1, "Hansen", 33);
        Customer cus2 = new Customer(2, "Jensen", 44);
        Customer cus3 = new Customer(3, "Svendsen", 55);
        city1.addCustomer(cus1);
        city1.addCustomer(cus2);
        city1.addCustomer(cus3);
      String jsonString = gson.toJson(cus1); // THIS IS BAD
//        CustomerDTO cDTO = new CustomerDTO(cus1);
//        String jsonString = gson.toJson(cDTO);
        System.out.println(jsonString);
    }
}
