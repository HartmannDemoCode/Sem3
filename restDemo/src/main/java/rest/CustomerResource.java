/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Customer;
import exceptions.CustomerNotFoundException;
import exceptions.ExceptionDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
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

    // SImple method to see if the service is running. Test with: /api/customer
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJson() {
        return Response.ok().entity(gson.toJson(customers.get(1))).build();
    }

    // Simple method to test use of Path annotation. Test with /api/customer/all
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomers() {
        return Response.ok().entity(gson.toJson(customers)).build();
    }

    // Method to test the use of semantic parameters. Test with /api/customer/3
    @GET
    @Path("/{id}") //with a sematic url parameter
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerById(@PathParam("id") int id) {
        return Response.ok().entity(gson.toJson(customers.get(id))).build();
    }
    
    // Method to test use of url query string parameters. Test with /api/customer/queryparam?olderThan=50
    @GET
    @Path("/queryparam") 
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> showUseOfQueryParam(@QueryParam("olderThan") int age) {
        List<Customer> all = new ArrayList(customers.values());
        List older = all.stream().filter((cus)->cus.getAge()>age).collect(Collectors.toList());
        return older;
    }

    // Method to test use of request parameters from a web form. Test with POST: /api/customer/ DATA: {"name": "Svend Auken","age": 82}
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postCustomer(String content) {
        Customer newCustomer = gson.fromJson(content, Customer.class);
        System.out.println("newCustomer: " + newCustomer);
        addCustomer(newCustomer);
        return Response.ok().entity(gson.toJson(newCustomer)).build();
    }

    // Simple errorhandling. Test the use of jersey WebApplicationException. Test with /api/customer/test/2 and /api/customer/test/10 to see difference look in browsers network tab for 404.
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
    // Test the jersey automatic conversion of List<Customer> to json (or xml based on the @produces media type). 
    // Test with /api/customer/test/all
    @GET
    @Path("/test/all")
    @Produces(MediaType.APPLICATION_JSON)
//    public Map<Integer, Customer> getCustomersALL() {
//        return customers; //Does not work - throws exception: WebApplicationException: com.sun.jersey.api.MessageException: A message body writer for Java class java.util.HashMap, and Java type java.util.Map<java.lang.Integer, entity.Customer>, and MIME media type application/json was not found.
    public List<Customer> getCustomersALL() {
        return new ArrayList(customers.values());
    }
    
    // ErrorHandling Test using the Exception DTO to wrap the Exception and send as json. 
    // Test with /api/customer/test/ex
    @GET
    @Path("/test/ex")
    @Produces(MediaType.APPLICATION_JSON)
    public String testExceptionDTO() {
        try {
            throw new NumberFormatException("Number must be an integer");
        } catch (NumberFormatException e) {
            ExceptionDTO exDTO = new ExceptionDTO(e, 406, true);
            return gson.toJson(exDTO);
        }
    }
    
    //Test using both the specific CustomerNotFoundExceptionHandler and the generic ExceptionMapper that will catch all the rest. 
    // Test with /api/customer/test/exmap/3 or api/customer/test/exmap/10 to see either the general server exception the specific CustomerNotFoundException.
    @GET
    @Path("/test/exmap/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String testExceptionMapper(@PathParam("id")int id) throws CustomerNotFoundException, Exception {
        Customer cus = findCustomer(id);
        if(cus == null)
            throw new CustomerNotFoundException("No customer for you I'm sorry");
        throw new Exception("Some server side error happend bla bla bla");
    }
    
    //2 private helper methods
    private void addCustomer(Customer customer) {
        int nextId = customers.size() + 1;
        customer.setId(nextId);
        customers.put(nextId, customer);
    }
    private Customer findCustomer(int id){
        return customers.get(id);
    }
}
