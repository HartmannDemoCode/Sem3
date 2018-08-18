/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;


/**
 * REST Web Service
 *
 * @author tha
 */
@Path("upload")
public class UploadResource {
//IMPORTANT: Replace with a unix path when uploading to Droplet and set permissions on the folder
  public static final String FILE_LOCATION = "C://img/";

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UploadResource
     */
    public UploadResource() {
    }

    /**
     * Retrieves representation of an instance of rest.UploadResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@Context UriInfo uriInfo) {
        
        //TODO return proper representation object
        return "{\"test\":\"test value\"}";
    }

  @POST
  @Path("/file")
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  @Produces(MediaType.APPLICATION_JSON)
  public Response uploadFile(@DefaultValue("") 
          @FormDataParam("user") String user,
          @FormDataParam("file") InputStream file, 
          @FormDataParam("file") FormDataContentDisposition fileDisposition) throws IOException {
    System.out.println("Just to show how to send additonal data: "+ user);
    String fileName = fileDisposition.getFileName();
    saveFile(file, fileName);
    String status = "{\"status\":\"uploaded\"}";
    return Response.ok(status).build();
  }

  private void saveFile(InputStream is, String fileLocation) throws IOException {
    String location = FILE_LOCATION + fileLocation;
    try (OutputStream os = new FileOutputStream(new File(location))) {
      byte[] buffer = new byte[256];
      int bytes = 0;
      while ((bytes = is.read(buffer)) != -1) {
        os.write(buffer, 0, bytes);
      }
    }
  }

}
