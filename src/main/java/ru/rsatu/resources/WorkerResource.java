package ru.rsatu.resources;

import io.vertx.core.json.JsonObject;
//import org.eclipse.microprofile.metrics.MetricUnits;
//import org.eclipse.microprofile.metrics.annotation.Timed;
import ru.rsatu.pojo.Workers;
import ru.rsatu.services.WorkerService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/workers")
public class WorkerResource {
    @Inject
    WorkerService ws;

    //@RolesAllowed({"watchAll"})
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getWorkersPage")
    //@Timed(name = "getWorkersPerPage", description = "getWorkersPage() called", unit = MetricUnits.MILLISECONDS)
    public Response getWorkersPage(@QueryParam("page") int page){
        JsonObject json = new JsonObject();
        json.put("page", page);
        json.put("per_page", 4);
        int c = ws.getCountWorkers();
        json.put("total", c);
        json.put("total_pages", (int)Math.ceil(c / 4.0));
        json.put("data", ws.getWorkersPage(page));
        return Response.ok(json).build();
    }

    //@RolesAllowed({"watchAll"})
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getWorkers")
    public Response getWorkers(){
        return Response.ok(ws.getWorkers()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getWorkersByDivision")
    public Response getWorkersByDivision(@QueryParam("divisionID") Long divisionID){
        return Response.ok(ws.getWorkersByDivision(divisionID)).build();
    }

    //@RolesAllowed({"watchAll"})
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getWorkerById")
    public Response getWorkerById(@QueryParam("workerID") Long workerID){
        return Response.ok(ws.getWorkerById(workerID)).build();
    }

    //@RolesAllowed({"editClients"})
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/insertWorker")
    public Response insertWorker(Workers w){
        return Response.ok(ws.insertWorker(w)).build();
    }

    //@RolesAllowed({"editClients"})
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/updateWorker")
    public Response updateWorker(Workers w){
        System.out.println("worker " + w.getName());
        return Response.ok(ws.updateWorker(w)).build();
    }

    //@RolesAllowed({"editClients"})
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/deleteWorker")
    public Response deleteWorker(@QueryParam("workerID") Long id){
        ws.deleteWorker(id);
        return Response.ok().build();
    }
}
