package ru.rsatu.services;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import javax.persistence.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.jose4j.json.internal.json_simple.JSONObject;
import ru.rsatu.pojo.MarksTimesheet;
import ru.rsatu.pojo.Workers;
import ru.rsatu.pojo.Timesheets;

@ApplicationScoped
public class TimesheetService {
    @Inject
    EntityManager em;

    @Transactional
    public void createTimesheet() {
    	Timesheets timesheet = new Timesheets();
        em.persist(timesheet);
    }

    //вставка данных
    @Transactional
    public Timesheets insertTimesheet(Timesheets ts) {
        ts.duration = 0;
        em.merge(ts);
        em.flush();
        em.clear();
        return ts;
    }

    //обновление данных
    @Transactional
    public Timesheets updateTimesheet(Timesheets ts) {
        em.merge(ts);
        em.flush();
        em.clear();
        return ts;
    }

    //удаление данных
    @Transactional
    public void deleteTimesheet(Timesheets ts) {
    	Timesheets ts1 = getTimesheetById(ts.getId());
        em.remove(ts1);
        em.flush();
    }

    public Timesheets getTimesheetById(Long id) {
    	Timesheets ts = em.find(Timesheets.class, id);
        return ts;
    }

    public List<Timesheets> getTimesheets(int page) {
        Query query = em.createQuery(" select s from Timesheets s ");
        query.setFirstResult((page-1)*10);
        query.setMaxResults(10);
        List<Timesheets> listTimesheets = query.getResultList();
        for (Timesheets timesheet : listTimesheets) {
            long min = TimeUnit.MILLISECONDS.toMinutes(timesheet.getExitDate().getTime() - timesheet.getEntryDate().getTime());
            long h = min / 60;
            double dur = h + (min - 60 * h)/60.00;
            timesheet.duration = Math.round(dur * 100) / 100.00;
        }
        return listTimesheets;
    }
    public List<Timesheets> getAllTimesheets() {
        return em.createQuery(" select c from Timesheets c ", Timesheets.class).getResultList();
    }
    
    public List<Timesheets> getTimesheetsByWorkerId(Long id) {
        Query query = em.createQuery(" select s from Timesheets s where worker_id="+id.toString());
        List<Timesheets> listTimesheets = query.getResultList();
        return listTimesheets;
    }
    
    public int countTimesheets() {
        Number timesheetsQTY = (Number) em.createQuery(" select count(id) from Timesheets ").getResultList().get(0);
        return timesheetsQTY.intValue() ;
    }
}
