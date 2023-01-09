package ru.rsatu.services;

import ru.rsatu.pojo.Vacations;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
@ApplicationScoped
public class VacationService {
    @Inject
    EntityManager em;

    //вставка данных
    @Transactional
    public Vacations insertVacation(Vacations v) {
        em.merge(v);
        em.flush();
        return v;
    }

    //обновление данных
    @Transactional
    public Vacations updateVacation(Vacations v) {
        em.merge(v);
        em.flush();
        return v;
    }

    //удаление данных
    @Transactional
    public void deleteVacation(Long ID) {
        Vacations v = this.getVacationById(ID);
        em.remove(v);
        em.flush();
    }

    public int getCountVacations() {
        Number number = (Number) em.createQuery(" select count(numberCommand) from Vacations ").getResultList().get(0);
        return number.intValue();
    }

    public List<Vacations> getVacations() {
        Query query = em.createQuery(" select v from Vacations v ");
        List<Vacations> listVacations = query.getResultList();
        return listVacations;
    }

    public List<Vacations> getVacationsByWorkerId(Long id) {
        Query query = em.createQuery(" select v from Vacations v where worker_id="+id.toString());
        List<Vacations> listVacations = query.getResultList();
        return listVacations;
    }
    public List<Vacations> getVacationsPage(int page) {
        Query query = em.createQuery(" select v from Vacations v ");
        query.setFirstResult((page-1)*4);
        query.setMaxResults(4);

        List<Vacations> listDivisions = query.getResultList();
        return listDivisions;
    }

    public Vacations getVacationById(Long ID) {
        Vacations v = em.find(Vacations.class, ID);
        return v;
    }
}
