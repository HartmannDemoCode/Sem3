package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import data.CustomerFacade;
import entity.Customer;
import exceptions.CustomerNotFoundException;
import exceptions.ExceptionDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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

    CustomerFacade cf = new CustomerFacade();

    public CustomerResource() {
    }

    // SImple method to see if the service is running. Test with: /api/customer
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJson() {
        return Response.ok().entity(gson.toJson(cf.getAllCustomers())).build();
    }

    // Simple method to test use of Path annotation. Test with /api/customer/allasmap
    @GET
    @Path("/allasmap")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomers() {
        return Response.ok().entity(gson.toJson(cf.getData())).build();
    }
    
    // Simple method to use with javascript (See the script.js file.
    // Test with /api/customer/allasarray Better for javascript to get an array of objects
    @GET
    @Path("/allasarray")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllasArray() {
        return Response.ok().entity(gson.toJson(cf.getData().values())).build();
    }

    // Method to test the use of semantic parameters. Test with /api/customer/3
    @GET
    @Path("/{id}") //with a sematic url parameter
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerById(@PathParam("id") int id) throws CustomerNotFoundException {
        Customer c = cf.getCustomerById(id);
        if(c==null) throw new CustomerNotFoundException("no customer with id: "+id);
        return Response.ok().entity(gson.toJson(c)).build();
    }
    
    // Method to test use of url query string parameters. Test with /api/customer/queryparam?olderThan=50
    @GET
    @Path("/queryparam") 
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> showUseOfQueryParam(@QueryParam("olderThan") int age) {
        List<Customer> all = new ArrayList(cf.getAllCustomers());
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
        Customer c = cf.createCustomer(newCustomer);
        return Response.ok().entity(gson.toJson(c)).build();
    }

    // Simple errorhandling. Test the use of jersey WebApplicationException. Test with /api/customer/test/2 and /api/customer/test/10 to see difference look in browsers network tab for 404.
    @GET
    @Path("/test/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer getCustomer(@PathParam("id") int id) {
        Customer cust = cf.getCustomerById(id);
        if (cust == null){
           throw new WebApplicationException(Response.Status.NOT_FOUND); //look for 404 not found in the browser.
        }
        return cust;
    }
    // Test the jersey automatic conversion of List<Customer> to json (or xml based on the @produces media type). 
    // Test with GET: /api/customer/test/all
    @GET
    @Path("/test/all")
    @Produces(MediaType.APPLICATION_JSON)
        public List<Customer> getCustomersALL() {
        return cf.getAllCustomers();
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
    
    // Test using both the specific CustomerNotFoundExceptionHandler and the generic ExceptionMapper that will catch all the rest. 
    // Test with /api/customer/test/exmap/3 or api/customer/test/exmap/10 to see either the general server exception the specific CustomerNotFoundException.
    @GET
    @Path("/test/exmap/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String testExceptionMapper(@PathParam("id")int id) throws CustomerNotFoundException, Exception {
        Customer cus = cf.getCustomerById(id);
        if(cus == null)
            throw new CustomerNotFoundException("No customer for you I'm sorry");
        throw new Exception("Some server side error happend bla bla bla");
    }
    
    // Method to test use PUT from script.js. Test with PUT: /api/customer/1 DATA: {"name": "Svend Auken","age": 82}
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCustomer( String content, @PathParam("id") int id) throws CustomerNotFoundException {
        Customer newCustomer = gson.fromJson(content, Customer.class);
        Customer savedCus = cf.getCustomerById(id);
        if(savedCus == null)
            throw new CustomerNotFoundException("no customer with id: "+id);
        if(newCustomer.getName()!=null)
            savedCus.setName(newCustomer.getName());
        if(newCustomer.getAge()!=0)
            savedCus.setAge(newCustomer.getAge());
        return Response.ok().entity(gson.toJson(savedCus)).build();
    }
    
    // Method to test use of DELETE form script.js. 
    // Test with DELETE: /api/customer/1 
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCustomer(@PathParam("id")int id) throws CustomerNotFoundException {
//        Customer cust = customers.get(id);
        Customer c = cf.deleteCustomer(id);
        if(c== null)
            throw new CustomerNotFoundException("no customer with id: "+id+" to delete");
        return Response.ok().entity(gson.toJson(c)).build();
    }
}
