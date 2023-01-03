package ru.rsatu.resources;

import io.vertx.core.json.JsonObject;
//import org.eclipse.microprofile.metrics.MetricUnits;
//import org.eclipse.microprofile.metrics.annotation.Timed;
import ru.rsatu.pojo.Vacations;
import ru.rsatu.services.VacationService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/vacations")
public class VacationResources {

    @Inject
    VacationService vs;

    //@RolesAllowed({"watchAll"})
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getVacationsPage")
    //@Timed(name = "getVacationsPerPage", description = "getVacationsPage() called", unit = MetricUnits.MILLISECONDS)
    public Response getVacationsPage(@QueryParam("page") int page){
        JsonObject json = new JsonObject();
        json.put("page", page);
        json.put("per_page", 4);
        int c = vs.getCountVacations();
        json.put("total", c);
        json.put("total_pages", (int)Math.ceil(c / 4.0));
        json.put("data", vs.getVacationsPage(page));
        return Response.ok(json).build();
    }

    //@RolesAllowed({"watchAll"})
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getVacations")
    public Response getVacations(){
        return Response.ok(vs.getVacations()).build();
    }

    //@RolesAllowed({"watchAll"})
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getVacationById")
    public Response getVacationById(@QueryParam("vacationID") Long ID){
        return Response.ok(vs.getVacationById(ID)).build();
    }

    //@RolesAllowed({"editClients"})
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/insertVacation")
    public Response insertVacation(Vacations v){
        return Response.ok(vs.insertVacation(v)).build();
    }

    //@RolesAllowed({"editClients"})
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/updateVacation")
    public Response updateVacation(Vacations v){
        System.out.println("Vacation " + v.getNumberCommand());
        return Response.ok(vs.updateVacation(v)).build();
    }

    //@RolesAllowed({"editClients"})
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/deleteVacation")
    public Response deleteVacation(@QueryParam("vacationID") Long id){
        vs.deleteVacation(id);
        return Response.ok().build();
    }
}
