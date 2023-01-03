package ru.rsatu.resources;

import io.quarkus.security.Authenticated;
import io.vertx.core.json.JsonObject;
import ru.rsatu.pojo.SickLeaves;
import ru.rsatu.services.SickLeaveService;
import ru.rsatu.services.TimesheetService;
//import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//@Authenticated
@Path("/sickleaves")
public class SickLeaveResource {
    @Inject
    SickLeaveService sls;
    
   // @RolesAllowed({"watchAll"})
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getSickLeaves")
    public Response getSickLeaves(@QueryParam("page") int page){
        JsonObject json = new JsonObject();
        json.put("page", page);
        json.put("per_page", 10);
        int c = sls.countSickLeaves();
        json.put("total", c);
        json.put("total_pages", (int)Math.ceil(c / 10.0));
        json.put("data", sls.getSickLeaves(page));
        return Response.ok(json).build();
    }
    //@RolesAllowed({"watchAll"})
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllSickLeaves")
    public Response SickLeaves(){
        return Response.ok(sls.getAllSickLeaves()).build();
    }
    //@RolesAllowed({"watchAll"})
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getSickLeaveById")
    public Response getSickLeaveById(@QueryParam("sickLeaveID") Long id){
        return Response.ok(sls.getSickLeaveById(id)).build();
    }
    //@RolesAllowed({"watchAll"})
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getSickLeavesByWorkerId")
    public Response getSickLeavesByWorkerId(@QueryParam("workerID") Long id){
        return Response.ok(sls.getSickLeavesByWorkerId(id)).build();
    }
    //@RolesAllowed({"watchAll"})
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getSickLeavesByMedicalOrganizationId")
    public Response getSickLeavesByMedicalOrganizationId(@QueryParam("medorgID") Long id){
        return Response.ok(sls.getSickLeavesByMedicalOrganizationId(id)).build();
    }
    //@RolesAllowed({"editSuppliers"})
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/insertSickLeave")
    public Response insertSickLeaves(SickLeaves sl){
        return Response.ok(sls.insertSickLeave(sl)).build();
    }
    //@RolesAllowed({"editTimesheets"})
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/updateSickLeave")
    public Response updateSupplier(SickLeaves sl){
        return Response.ok(sls.updateSickLeave(sl)).build();
    }
    //@RolesAllowed({"editSuppliers"})
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/deleteSupplier")
    public Response deleteSickLeave(@QueryParam("sickLeaveID") Long id){
        sls.deleteSickLeave(sls.getSickLeaveById(id));
        return Response.ok().build();
    }
}
