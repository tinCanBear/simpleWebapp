package mywebapp;

import javax.servlet.http.HttpServlet;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Hello world!
 *
 */
@Path("data")
public class Resource{
    HazelCastServer hcs = HazelCastServer.getInstance();
    @GET
    @Path("{index}")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloWorld(@PathParam("index") int index) {
        return hcs.getData(index);
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getData(String str){
        return Integer.toString(hcs.insertData(str));
    }
}