package ru.rsatu.resources;

import io.quarkus.security.Authenticated;
import io.vertx.core.json.JsonObject;
import org.jose4j.json.internal.json_simple.JSONObject;
import ru.rsatu.pojo.BusinessTrip;
import ru.rsatu.pojo.SickLeaves;
import ru.rsatu.pojo.Timesheets;
import ru.rsatu.pojo.Vacations;
import ru.rsatu.services.BusinessTripService;
import ru.rsatu.services.SickLeaveService;
import ru.rsatu.services.TimesheetService;
import ru.rsatu.services.VacationService;
//import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Authenticated
@Path("/timesheets")
public class TimesheetResource {
    @Inject
    TimesheetService ts;
    @Inject
    SickLeaveService sls;
    @Inject
    BusinessTripService bts;
    @Inject
    VacationService vs;
    
    @RolesAllowed({"watchTimesheet"})
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
    @RolesAllowed({"watchTimesheet"})
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getTimesheets")
    public Response Timesheets(){
        return Response.ok(ts.getAllTimesheets()).build();
    }
    @RolesAllowed({"watchTimesheet"})
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getTimesheetById")
    public Response getTimesheetById(@QueryParam("timesheetID") Long id){
        return Response.ok(ts.getTimesheetById(id)).build();
    }
    @RolesAllowed({"watchTimesheet"})
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getTimesheetsByWorkerId")
    public Response getTimesheetsByWorkerId(@QueryParam("workerID") Long id,
                                            @QueryParam("beginDate") String beginDate,
                                            @QueryParam("endDate") String endDate){
        LocalDate date1 = LocalDate.parse((String)(beginDate).toString());
        LocalDate date2 = LocalDate.parse((String)(endDate).toString());

        List<Timesheets> listTimesheets = ts.getTimesheetsByWorkerId(id, beginDate, endDate);
        List<SickLeaves> listSickLeaves = sls.getSickLeavesByWorkerIdDate(id, beginDate, endDate);
        List<BusinessTrip> listBusinessTrips = bts.getBusinessTripsByWorkerId(id, beginDate, endDate);
        List<Vacations> listVacations = vs.getVacationsByWorkerId(id, beginDate, endDate);

        List<JSONObject> listJSONObject = new ArrayList<JSONObject>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        double sumWorkHour = 0;
        for (Timesheets timesheet : listTimesheets) {
            long min = TimeUnit.MILLISECONDS.toMinutes(timesheet.getExitDate().getTime() - timesheet.getEntryDate().getTime());
            long h = min / 60;
            double dur = h + (min - 60 * h)/60.00;
            timesheet.duration = Math.round(dur * 100) / 100.00;
            JSONObject jo = new JSONObject();
            jo.put("mark", timesheet.marksTimesheet.getMark());
            jo.put("date", formatter.format(timesheet.getEntryDate()));
            double d = Math.round(dur * 100) / 100.00;
            sumWorkHour += d;
            jo.put("duration", d);
            listJSONObject.add(jo);
        }

        int daySickLeave = 0;
            for (SickLeaves sickLeave : listSickLeaves) {
                LocalDate start = LocalDate.parse(formatter.format(sickLeave.getBeginDate()).toString());
                LocalDate end = LocalDate.parse(formatter.format(sickLeave.getEndDate()).toString());

                for (LocalDate curDate = start; curDate.isBefore(end.plusDays(1)); curDate=curDate.plusDays(1)) {
                    if (curDate.isAfter(date1.minusDays(1)) && curDate.isBefore(date2.plusDays(1)))
                    {
                        JSONObject jo = new JSONObject();
                        jo.put("mark", "Б");
                        jo.put("date", curDate);
                        jo.put("duration", 0);
                        listJSONObject.add(jo);
                        daySickLeave++;
                    }
                }

            }
        int dayBusinessTrip = 0;
        for (BusinessTrip businessTrip : listBusinessTrips) {
            LocalDate start = LocalDate.parse(formatter.format(businessTrip.getBeginDate()).toString());
            LocalDate end = LocalDate.parse(formatter.format(businessTrip.getEndDate()).toString());
            for (LocalDate curDate = start; curDate.isBefore(end.plusDays(1)); curDate=curDate.plusDays(1)){
                if (curDate.isAfter(date1.minusDays(1)) && curDate.isBefore(date2.plusDays(1))){
                    JSONObject jo = new JSONObject();
                    jo.put("mark", "K");
                    jo.put("date", curDate);
                    jo.put("duration", 0);
                    listJSONObject.add(jo);
                    dayBusinessTrip++;
                }
            }
        }


        int dayVacation = 0;
        for (Vacations vacation : listVacations) {
            LocalDate start = LocalDate.parse(formatter.format(vacation.getBeginDate()).toString());
            LocalDate end = LocalDate.parse(formatter.format(vacation.getEndDate()).toString());
            for (LocalDate curDate = start; curDate.isBefore(end.plusDays(1)); curDate=curDate.plusDays(1)){
                if (curDate.isAfter(date1.minusDays(1)) && curDate.isBefore(date2.plusDays(1))){
                    JSONObject jo = new JSONObject();
                    jo.put("mark", "ОТ");
                    jo.put("date", curDate);
                    jo.put("duration", 0);
                    listJSONObject.add(jo);
                    dayVacation++;
                }
            }
      }

        JSONObject temp = new JSONObject();
        boolean sorted = false;
        
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < listJSONObject.size()-1; i++) {
                date1 = LocalDate.parse((String)(listJSONObject.get(i).get("date")).toString());
                date2 = LocalDate.parse((String)(listJSONObject.get(i+1).get("date")).toString());
                if ((date2.isBefore(date1))) {
                	listJSONObject.get(i);
                    temp = listJSONObject.get(i);
                    listJSONObject.set(i, listJSONObject.get(i + 1));
                    listJSONObject.set(i + 1, temp);
                    sorted = false;
                }
            }
        }
        
        JsonObject json = new JsonObject();
        json.put("page", 1);
        //json.put("per_page", 10);
        json.put("per_page", listJSONObject.size());
        int c = listJSONObject.size();
        json.put("total", c);
        //json.put("total_pages", (int)Math.ceil(c / 10.0));
        json.put("total_pages", 1);
        json.put("data", listJSONObject);
        json.put("totalWorkHours", sumWorkHour);
        json.put("daySickLeave", daySickLeave);
        json.put("dayBusinessTrip", dayBusinessTrip);
        json.put("dayVacation", dayVacation);
        return Response.ok(json).build();
    }
    @RolesAllowed({"changeTimesheet"})
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/insertTimesheet")
    public Response insertTimesheet(Timesheets t){
        System.out.println(t.toString());
        return Response.ok(ts.insertTimesheet(t)).build();
    }
    @RolesAllowed({"changeTimesheet"})
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/updateTimesheet")
    public Response updateTimesheet(Timesheets t){
        return Response.ok(ts.updateTimesheet(t)).build();
    }
    @RolesAllowed({"changeTimesheet"})
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/deleteTimesheet")
    public Response deleteTimesheet(@QueryParam("timesheetID") Long id){
        ts.deleteTimesheet(ts.getTimesheetById(id));
        return Response.ok().build();
    }
}
