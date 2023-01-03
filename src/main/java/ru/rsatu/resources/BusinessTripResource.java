package ru.rsatu.resources;

import io.vertx.core.json.JsonObject;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Timed;
import ru.rsatu.pojo.BusinessTrip;
import ru.rsatu.services.BusinessTripService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/businesstrips")
public class BusinessTripResource {
    @Inject
    BusinessTripService bts;

    //@RolesAllowed({"watchAll"})
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getBusinessTripPage")
    @Timed(name = "getBusinessTripPerPage", description = "getBusinessTripPage() called", unit = MetricUnits.MILLISECONDS)
    public Response getBusinessTripPage(@QueryParam("page") int page){
        JsonObject json = new JsonObject();
        json.put("page", page);
        json.put("per_page", 4);
        int c = bts.getCountBusinessTrip();
        json.put("total", c);
        json.put("total_pages", (int)Math.ceil(c / 4.0));
        json.put("data", bts.getBusinessTripsPage(page));
        return Response.ok(json).build();
    }

    //@RolesAllowed({"watchAll"})
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getBusinessTrip")
    public Response getBusinessTrip(){
        return Response.ok(bts.getBusinessTrips()).build();
    }

    //@RolesAllowed({"watchAll"})
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getBusinessTripById")
    public Response getBusinessTripById(@QueryParam("businessTripID") Long ID){
        return Response.ok(bts.getBusinessTripById(ID)).build();
    }

    //@RolesAllowed({"editClients"})
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/insertBusinessTrip")
    public Response insertBusinessTrip(BusinessTrip d){
        return Response.ok(bts.insertBusinessTrip(d)).build();
    }

    //@RolesAllowed({"editClients"})
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/updateBusinessTrip")
    public Response updateDivision(BusinessTrip d){
        System.out.println("BusinessTrip " + d.getNumberCommand());
        return Response.ok(bts.updateBusinessTrip(d)).build();
    }

    //@RolesAllowed({"editClients"})
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/deleteBusinessTrip")
    public Response deleteBusinessTrip(@QueryParam("businessTripID") Long id){
        bts.deleteBusinessTrip(id);
        return Response.ok().build();
    }
}
