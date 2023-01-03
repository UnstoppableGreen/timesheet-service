package ru.rsatu.services;

import ru.rsatu.pojo.BusinessTrip;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
@ApplicationScoped
public class BusinessTripService {

    @Inject
    EntityManager em;

    //вставка данных
    @Transactional
    public BusinessTrip insertBusinessTrip(BusinessTrip b) {
        em.merge(b);
        em.flush();
        return b;
    }

    //обновление данных
    @Transactional
    public BusinessTrip updateBusinessTrip(BusinessTrip b) {
        em.merge(b);
        em.flush();
        return b;
    }

    //удаление данных
    @Transactional
    public void deleteBusinessTrip(Long ID) {
        BusinessTrip b = this.getBusinessTripById(ID);
        em.remove(b);
        em.flush();
    }

    public int getCountBusinessTrip() {
        Number number = (Number) em.createQuery(" select count(numberCommand) from BusinessTrip ").getResultList().get(0);
        return number.intValue();
    }

    public List<BusinessTrip> getBusinessTrips() {
        Query query = em.createQuery(" select b from BusinessTrip b ");
        List<BusinessTrip> listBusinessTrip = query.getResultList();
        return listBusinessTrip;
    }

    public List<BusinessTrip> getBusinessTripsPage(int page) {
        Query query = em.createQuery(" select b from BusinessTrip b ");
        query.setFirstResult((page-1)*4);
        query.setMaxResults(4);

        List<BusinessTrip> listBusinessTrip = query.getResultList();
        return listBusinessTrip;
    }

    public BusinessTrip getBusinessTripById(Long ID) {
        BusinessTrip b = em.find(BusinessTrip.class, ID);
        return b;
    }
}
