package com.epam.lab.java.web.webapi;

import com.epam.lab.java.web.jaxb.Food;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("instance")
@Produces(APPLICATION_JSON)
public class InstanceResource implements JsonRestfulWebResource {

//    private InstanceDao instanceDao;

//    @Context
//    public void init(ServletContext context) {
//        instanceDao = (InstanceDao) context.getAttribute(Initer.INSTANCE_DAO);
//    }

//    @GET
//    public Response findAll() {
//        final Collection<> instances = instanceDao.findAll();
//        return instances.isEmpty() ? ok(instances): noContent();
//    }

    @GET
    @Path("{id}")
    public Response get(@PathParam("id") int id) {
        return ok(new Food()
                .setId(id)
                .setName("nnn")
                .setDescription("ddd")
                .setCalories(234)
                .setPrice("333"));

//        return instanceDao.getById(id)
//                .map(this::ok)
//                .orElse(noContent());
    }
}