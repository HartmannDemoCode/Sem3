/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Customer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author thomas
 */
@Path("customer")
public class CustomerResource {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    @Context
    private UriInfo context;

    private static Map<Integer, Customer> customers = new HashMap();

    {
        customers.put(1, new Customer(1, "Oluf Palme", 96));
        customers.put(2, new Customer(2, "Sarah Palin", 56));
        customers.put(3, new Customer(3, "Vladimir Putin", 12));
    }

    public CustomerResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJson() {
        return Response.ok().entity(gson.toJson(customers.get(1))).build();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomers() {
        return Response.ok().entity(gson.toJson(customers)).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerById(@PathParam("id") int id) {
        return Response.ok().entity(gson.toJson(customers.get(id))).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postCustomer(String content) {
        Customer newCustomer = gson.fromJson(content, Customer.class);
        System.out.println("newCustomer: " + newCustomer);
        addCustomer(newCustomer);
        return Response.ok().entity(gson.toJson(newCustomer)).build();
    }


    @GET
    @Path("/test/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer getCustomer(@PathParam("id") int id) {
        Customer cust = findCustomer(id);
        if (cust == null){
           throw new WebApplicationException(Response.Status.NOT_FOUND); //look for 404 not found in the browser.
        }
        return cust;
    }
    @GET
    @Path("/test/all")
    @Produces(MediaType.APPLICATION_JSON)
//    public Map<Integer, Customer> getCustomersALL() {
//        return customers; //Does not work - throws exception: WebApplicationException: com.sun.jersey.api.MessageException: A message body writer for Java class java.util.HashMap, and Java type java.util.Map<java.lang.Integer, entity.Customer>, and MIME media type application/json was not found.
    public List<Customer> getCustomersALL() {
        return new ArrayList(customers.values());
    }
    
    private void addCustomer(Customer customer) {
        int nextId = customers.size() + 1;
        customer.setId(nextId);
        customers.put(nextId, customer);
    }
    private Customer findCustomer(int id){
        return customers.get(id);
    }
}
