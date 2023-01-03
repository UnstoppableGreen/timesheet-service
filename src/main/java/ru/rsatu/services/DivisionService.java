package ru.rsatu.services;

import ru.rsatu.pojo.Divisions;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
@ApplicationScoped

public class DivisionService {

    @Inject
    EntityManager em;

    //вставка данных
    @Transactional
    public Divisions insertDivision(Divisions d) {
        em.merge(d);
        em.flush();
        return d;
    }

    //обновление данных
    @Transactional
    public Divisions updateDivision(Divisions d) {
        em.merge(d);
        em.flush();
        return d;
    }

    //удаление данных
    @Transactional
    public void deleteDivision(Long divisionID) {
        Divisions d = this.getDivisionById(divisionID);
        em.remove(d);
        em.flush();
    }

    public int getCountDivisions() {
        Number number = (Number) em.createQuery(" select count(name) from Divisions ").getResultList().get(0);
        return number.intValue();
    }

    public List<Divisions> getDivisions() {
        Query query = em.createQuery(" select d from Divisions d ");
        List<Divisions> listDivisions = query.getResultList();
        return listDivisions;
    }

    public List<Divisions> getDivisionsPage(int page) {
        Query query = em.createQuery(" select d from Divisions d ");
        query.setFirstResult((page-1)*4);
        query.setMaxResults(4);

        List<Divisions> listDivisions = query.getResultList();
        return listDivisions;
    }

    public Divisions getDivisionById(Long divisionID) {
        Divisions d = em.find(Divisions.class, divisionID);
        return d;
    }
}
