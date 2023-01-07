package ru.rsatu.resources;

import io.quarkus.security.Authenticated;
import io.vertx.core.json.JsonObject;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Timed;
import ru.rsatu.pojo.*;
import ru.rsatu.services.ReferenceService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//mark
//medical
//operatingmode
//professio
//typeofdisability

@Authenticated
@Path("/references")
public class ReferenceResource {
    @Inject
    ReferenceService rs;

    //------------------------------------------------------------------------------------------------------------------
    //Professions
    //------------------------------------------------------------------------------------------------------------------

    @RolesAllowed({"watchReferences"})
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getProfessionsPage")
    @Timed(name = "getProfessionsPage", description = "getProfessionsPage() called", unit = MetricUnits.MILLISECONDS)
    public Response getProfessionsPage(@QueryParam("page") int page){
        JsonObject json = new JsonObject();
        json.put("page", page);
        json.put("per_page", 4);
        int c = rs.getCountProfessions();
        json.put("total", c);
        json.put("total_pages", (int)Math.ceil(c / 4.0));
        json.put("data", rs.getProfessionsPage(page));
        return Response.ok(json).build();
    }

    @RolesAllowed({"watchReferences"})
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getProfessions")
    public Response getProfessions(){
        return Response.ok(rs.getProfessions()).build();
    }


    @RolesAllowed({"watchReferences"})
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getProfessionById")
    public Response getProfessionById(@QueryParam("professionID") Long professionID){
        return Response.ok(rs.getProfessionById(professionID)).build();
    }

    @RolesAllowed({"changeReferences"})
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/insertProfession")
    public Response insertProfession(Profession p){
        return Response.ok(rs.insertProfession(p)).build();
    }

    @RolesAllowed({"changeReferences"})
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/updateProfession")
    public Response updateProfession(Profession p){
        System.out.println("Profession " + p.getName());
        return Response.ok(rs.updateProfession(p)).build();
    }

    @RolesAllowed({"changeReferences"})
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/deleteProfession")
    public Response deleteProfession(@QueryParam("professionID") Long id){
        rs.deleteProfession(id);
        return Response.ok().build();
    }

    //------------------------------------------------------------------------------------------------------------------
    //MedicalOrganization
    //------------------------------------------------------------------------------------------------------------------
    @RolesAllowed({"watchReferences"})
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getMedicalOrganizationsPage")
    @Timed(name = "getMedicalOrganizationPerPage", description = "getMedicalOrganizationPage() called", unit = MetricUnits.MILLISECONDS)
    public Response getMedicalOrganizationPage(@QueryParam("page") int page){
        JsonObject json = new JsonObject();
        json.put("page", page);
        json.put("per_page", 4);
        int c = rs.getCountMedicalOrganization();
        json.put("total", c);
        json.put("total_pages", (int)Math.ceil(c / 4.0));
        json.put("data", rs.getMedicalOrganizationPage(page));
        return Response.ok(json).build();
    }

    @RolesAllowed({"watchReferences"})
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getMedicalOrganizations")
    public Response getMedicalOrganizations(){
        return Response.ok(rs.getMedicalOrganizations()).build();
    }


    @RolesAllowed({"watchReferences"})
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getMedicalOrganizationById")
    public Response getMedicalOrganizationById(@QueryParam("medicalOrganizationID") Long medOrgID){
        return Response.ok(rs.getMedicalOrganizationById(medOrgID)).build();
    }

    @RolesAllowed({"changeReferences"})
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/insertMedicalOrganization")
    public Response insertMedicalOrganization(MedicalOrganization m){
        return Response.ok(rs.insertMedicalOrganization(m)).build();
    }

    @RolesAllowed({"changeReferences"})
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/updateMedicalOrganization")
    public Response updateMedicalOrganization(MedicalOrganization m){
        System.out.println("MedicalOrganization " + m.getName());
        return Response.ok(rs.updateMedicalOrganization(m)).build();
    }

    @RolesAllowed({"changeReferences"})
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/deleteMedicalOrganization")
    public Response deleteMedicalOrganization(@QueryParam("medicalOrganizationID") Long id){
        rs.deleteMedicalOrganization(id);
        return Response.ok().build();
    }
    //------------------------------------------------------------------------------------------------------------------
    //MarksTimesheet
    //------------------------------------------------------------------------------------------------------------------
    @RolesAllowed({"watchReferences"})
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getMarksTimesheetPage")
    @Timed(name = "getMarksTimesheetPerPage", description = "getMarksTimesheetPage() called", unit = MetricUnits.MILLISECONDS)
    public Response getMarksTimesheetPage(@QueryParam("page") int page){
        JsonObject json = new JsonObject();
        json.put("page", page);
        json.put("per_page", 4);
        int c = rs.getCountMarksTimesheet();
        json.put("total", c);
        json.put("total_pages", (int)Math.ceil(c / 4.0));
        json.put("data", rs.getMarksTimesheetPage(page));
        return Response.ok(json).build();
    }

    @RolesAllowed({"watchReferences"})
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getMarksTimesheet")
    public Response getMarksTimesheet(){
        return Response.ok(rs.getMarksTimesheet()).build();
    }


    @RolesAllowed({"watchReferences"})
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getMarksTimesheetById")
    public Response getMarksTimesheetById(@QueryParam("marksTimesheetID") Long marksTimesheetID){
        return Response.ok(rs.getMarksTimesheetById(marksTimesheetID)).build();
    }

    @RolesAllowed({"changeReferences"})
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/insertMarksTimesheet")
    public Response insertMarksTimesheet(MarksTimesheet p){
        return Response.ok(rs.insertMarksTimesheet(p)).build();
    }

    @RolesAllowed({"changeReferences"})
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/updateMarksTimesheet")
    public Response updateMarksTimesheet(MarksTimesheet p){
        System.out.println("MarksTimesheet " + p.getMark());
        return Response.ok(rs.updateMarksTimesheet(p)).build();
    }

    @RolesAllowed({"changeRevfences"})
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/deleteMarksTimesheet")
    public Response deleteMarksTimesheet(@QueryParam("marksTimesheetID") Long id){
        rs.deleteMarksTimesheet(id);
        return Response.ok().build();
    }
    //------------------------------------------------------------------------------------------------------------------
    //OperatingMode
    //------------------------------------------------------------------------------------------------------------------
    @RolesAllowed({"watchReferences"})
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getOperatingModePage")
    @Timed(name = "getOperatingModePerPage", description = "getOperatingModePage() called", unit = MetricUnits.MILLISECONDS)
    public Response getOperatingModePage(@QueryParam("page") int page){
        JsonObject json = new JsonObject();
        json.put("page", page);
        json.put("per_page", 4);
        int c = rs.getCountOperatingMode();
        json.put("total", c);
        json.put("total_pages", (int)Math.ceil(c / 4.0));
        json.put("data", rs.getOperatingModePage(page));
        return Response.ok(json).build();
    }

    @RolesAllowed({"watchReferences"})
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getOperatingMode")
    public Response getOperatingMode(){
        return Response.ok(rs.getOperatingMode()).build();
    }


    @RolesAllowed({"watchReferences"})
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getOperatingModeById")
    public Response getOperatingModeById(@QueryParam("operatingModeID") Long ID){
        return Response.ok(rs.getOperatingModeById(ID)).build();
    }

    @RolesAllowed({"changeReferences"})
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/insertOperatingMode")
    public Response insertOperatingMode(OperatingMode p){
        return Response.ok(rs.insertOperatingMode(p)).build();
    }

    @RolesAllowed({"changeReferences"})
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/updateOperatingMode")
    public Response updateOperatingMode(OperatingMode o){
        System.out.println("OperatingMode " + o.getName());
        return Response.ok(rs.updateOperatingMode(o)).build();
    }

    @RolesAllowed({"changeReferences"})
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/deleteOperatingMode")
    public Response deleteOperatingMode(@QueryParam("operatingModeID") Long id){
        rs.deleteOperatingMode(id);
        return Response.ok().build();
    }
    //------------------------------------------------------------------------------------------------------------------
    //TypeOfDisability
    //------------------------------------------------------------------------------------------------------------------
    @RolesAllowed({"watchReferences"})
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getTypeOfDisabilityPage")
    @Timed(name = "getTypeOfDisabilityPerPage", description = "getTypeOfDisabilityPage() called", unit = MetricUnits.MILLISECONDS)
    public Response getTypeOfDisabilityPage(@QueryParam("page") int page){
        JsonObject json = new JsonObject();
        json.put("page", page);
        json.put("per_page", 4);
        int c = rs.getCountTypeOfDisability();
        json.put("total", c);
        json.put("total_pages", (int)Math.ceil(c / 4.0));
        json.put("data", rs.getTypeOfDisabilityPage(page));
        return Response.ok(json).build();
    }

    @RolesAllowed({"watchReferences"})
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getTypeOfDisability")
    public Response getTypeOfDisability(){
        return Response.ok(rs.getTypeOfDisability()).build();
    }


    @RolesAllowed({"watchReferences"})
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getTypeOfDisabilityById")
    public Response getTypeOfDisabilityById(@QueryParam("typeOfDisabilityID") Long typeOfDisabilityID){
        return Response.ok(rs.getTypeOfDisabilityById(typeOfDisabilityID)).build();
    }

    @RolesAllowed({"changeReferences"})
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/insertTypeOfDisability")
    public Response insertTypeOfDisability(TypeOfDisability t){
        return Response.ok(rs.insertTypeOfDisability(t)).build();
    }

    @RolesAllowed({"changeReferences"})
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/updateTypeOfDisability")
    public Response updateTypeOfDisability(TypeOfDisability t){
        System.out.println("TypeOfDisability " + t.getName());
        return Response.ok(rs.updateTypeOfDisability(t)).build();
    }

    @RolesAllowed({"changeReferences"})
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/deleteTypeOfDisability")
    public Response deleteTypeOfDisability(@QueryParam("typeOfDisabilityID") Long id){
        rs.deleteTypeOfDisability(id);
        return Response.ok().build();
    }


}
