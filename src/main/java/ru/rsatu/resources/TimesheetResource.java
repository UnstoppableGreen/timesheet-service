package ru.rsatu.resources;

import io.quarkus.security.Authenticated;
import io.vertx.core.json.JsonObject;
import ru.rsatu.pojo.Timesheets;
import ru.rsatu.services.TimesheetService;
//import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//@Authenticated
@Path("/timesheets")
public class TimesheetResource {
    @Inject
    TimesheetService ts;
    
   // @RolesAllowed({"watchAll"})
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getTimesheetsByPage")
    public Response getTimesheets(@QueryParam("page") int page){
        JsonObject json = new JsonObject();
        json.put("page", page);
        json.put("per_page", 10);
        int c = ts.countTimesheets();
        json.put("total", c);
        json.put("total_pages", (int)Math.ceil(c / 10.0));
        json.put("data", ts.getTimesheets(page));
        return Response.ok(json).build();
    }
    //@RolesAllowed({"watchAll"})
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getTimesheets")
    public Response Timesheets(){
        return Response.ok(ts.getAllTimesheets()).build();
    }
    //@RolesAllowed({"watchAll"})
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getTimesheetById")
    public Response getTimesheetById(@QueryParam("timesheetID") Long id){
        return Response.ok(ts.getTimesheetById(id)).build();
    }
    //@RolesAllowed({"watchAll"})
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getTimesheetByWorkerId")
    public Response getTimesheetsByWorkerId(@QueryParam("workerID") Long id){
        return Response.ok(ts.getTimesheetsByWorkerId(id)).build();
    }
    //@RolesAllowed({"editSuppliers"})
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/insertTimesheet")
    public Response insertTimesheet(Timesheets t){
        System.out.println(t.toString());
        return Response.ok(ts.insertTimesheet(t)).build();
    }
    //@RolesAllowed({"editTimesheets"})
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/updateTimesheet")
    public Response updateTimesheet(Timesheets t){
        return Response.ok(ts.updateTimesheet(t)).build();
    }
    //@RolesAllowed({"editSuppliers"})
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/deleteTimesheet")
    public Response deleteTimesheet(@QueryParam("timesheetID") Long id){
        ts.deleteTimesheet(ts.getTimesheetById(id));
        return Response.ok().build();
    }
}
