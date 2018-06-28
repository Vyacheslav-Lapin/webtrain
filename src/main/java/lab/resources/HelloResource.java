package lab.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("hello")
@Produces(APPLICATION_JSON)
public class HelloResource implements JsonRestfulWebResource {

    @GET
    public Response hello() {
        return ok("Goodbye!");
    }

}
