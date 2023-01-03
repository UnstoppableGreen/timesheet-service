package ru.rsatu.services;

import ru.rsatu.pojo.Workers;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class WorkerService {
    @Inject
    EntityManager em;

    //вставка данных
    @Transactional
    public Workers insertWorker(Workers w) {
        em.merge(w);
        em.flush();
        return w;
    }

    //обновление данных
    @Transactional
    public Workers updateWorker(Workers w) {
        em.merge(w);
        em.flush();
        return w;
    }

    //удаление данных
    @Transactional
    public void deleteWorker(Long workerID) {
        Workers c = this.getWorkerById(workerID);
        em.remove(c);
        em.flush();
    }

    public int getCountWorkers() {
        Number number = (Number) em.createQuery(" select count(name) from Workers ").getResultList().get(0);
        return number.intValue();
    }

    public List<Workers> getWorkers() {
        Query query = em.createQuery(" select w from Workers w ");
        List<Workers> listWorkers = query.getResultList();
        return listWorkers;
    }

    public List<Workers> getWorkersByDivision(Long divisionID) {
        Query query = em.createQuery(" select w from Workers w where division_id = " + divisionID.toString());
        List<Workers> listWorkers = query.getResultList();
        return listWorkers;
    }

    public List<Workers> getWorkersPage(int page) {
        Query query = em.createQuery(" select w from Workers w ");
        query.setFirstResult((page-1)*4);
        query.setMaxResults(4);

        List<Workers> listWorkers = query.getResultList();
        return listWorkers;
    }

    public Workers getWorkerById(Long workerID) {
        Workers w = em.find(Workers.class, workerID);
        return w;
    }
}
