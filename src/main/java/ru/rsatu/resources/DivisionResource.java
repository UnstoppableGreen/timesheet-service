package ru.rsatu.resources;

import io.quarkus.security.Authenticated;
import io.vertx.core.json.JsonObject;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Timed;
import ru.rsatu.pojo.Divisions;
import ru.rsatu.services.DivisionService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
@Authenticated
@Path("/divisions")
public class DivisionResource {

    @Inject
    DivisionService ds;

    //@RolesAllowed({"watchAll"})
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getDivisionsPage")
    @Timed(name = "getDivisionsPerPage", description = "getDivisionsPage() called", unit = MetricUnits.MILLISECONDS)
    public Response getDivisionsPage(@QueryParam("page") int page){
        JsonObject json = new JsonObject();
        json.put("page", page);
        json.put("per_page", 4);
        int c = ds.getCountDivisions();
        json.put("total", c);
        json.put("total_pages", (int)Math.ceil(c / 4.0));
        json.put("data", ds.getDivisionsPage(page));
        return Response.ok(json).build();
    }

    //@RolesAllowed({"watchAll"})
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getDivisions")
    public Response getDivisions(){
        return Response.ok(ds.getDivisions()).build();
    }

    //@RolesAllowed({"watchAll"})
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getDivisionById")
    public Response getDivisionById(@QueryParam("divisionID") Long divisionID){
        return Response.ok(ds.getDivisionById(divisionID)).build();
    }

    //@RolesAllowed({"editClients"})
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/insertDivision")
    public Response insertDivision(Divisions d){
        return Response.ok(ds.insertDivision(d)).build();
    }

    //@RolesAllowed({"editClients"})
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/updateDivision")
    public Response updateDivision(Divisions d){
        System.out.println("Division " + d.getName());
        return Response.ok(ds.updateDivision(d)).build();
    }

    //@RolesAllowed({"editClients"})
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/deleteDivision")
    public Response deleteDivision(@QueryParam("divisionID") Long id){
        ds.deleteDivision(id);
        return Response.ok().build();
    }
}
