package ru.rsatu.services;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import ru.rsatu.pojo.SickLeaves;

@ApplicationScoped
public class SickLeaveService {
    @Inject
    EntityManager em;
    
    @Inject
    ReferenceService rs;
    
    @Inject
    WorkerService ws;

    @Transactional
    public void createSickLeave() {
    	SickLeaves sickLeave = new SickLeaves();
        em.persist(sickLeave);
    }

    //вставка данных
    @Transactional
    public SickLeaves insertSickLeave(SickLeaves sl) {
        em.merge(sl);
        em.flush();
        em.clear();
        return sl;
    }

    //обновление данных
    @Transactional
    public SickLeaves updateSickLeave(SickLeaves sl) {
        em.merge(sl);
        em.flush();
        em.clear();
        return sl;
    }

    //удаление данных
    @Transactional
    public void deleteSickLeave(SickLeaves sl) {
    	SickLeaves sl1 = getSickLeaveById(sl.getId());
        em.remove(sl1);
        em.flush();
    }

    public SickLeaves getSickLeaveById(Long id) {
    	SickLeaves sl = em.find(SickLeaves.class, id);
        return sl;
    }

    public List<SickLeaves> getSickLeaves(int page) {
        Query query = em.createQuery(" select s from SickLeaves s ");
        query.setFirstResult((page-1)*10);
        query.setMaxResults(10);
        List<SickLeaves> listSickLeave = query.getResultList();
        return listSickLeave;
    }
    public List<SickLeaves> getAllSickLeaves() {
        return em.createQuery(" select c from SickLeaves c ", SickLeaves.class).getResultList();
    }
    
    public List<SickLeaves> getSickLeavesByWorkerId(Long id) {
        System.out.println("getSickLeavesByWorkerId " + id);
        Query query = em.createQuery(" select s from SickLeaves s where worker_id="+id.toString());
        List<SickLeaves> listSickLeaves = query.getResultList();
        return listSickLeaves;
    }


    public List<SickLeaves> getSickLeavesByMedicalOrganizationId(Long id) {
        Query query = em.createQuery(" select s from SickLeaves s where medicalorganization_id="+id.toString());
        List<SickLeaves> listSickLeaves = query.getResultList();
        return listSickLeaves;
    }
    
    public int countSickLeaves() {
        Number timesheetsQTY = (Number) em.createQuery(" select count(id) from SickLeaves ").getResultList().get(0);
        return timesheetsQTY.intValue() ;
    }
}
