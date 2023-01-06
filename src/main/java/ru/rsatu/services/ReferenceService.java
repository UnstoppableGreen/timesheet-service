package ru.rsatu.services;

import ru.rsatu.pojo.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ReferenceService {

    @Inject
    EntityManager em;

    //------------------------------------------------------------------------------------------------------------------
    //Professions
    //------------------------------------------------------------------------------------------------------------------
    //вставка данных
    @Transactional
    public Profession insertProfession(Profession p) {
        em.merge(p);
        em.flush();
        return p;
    }

    //обновление данных
    @Transactional
    public Profession updateProfession(Profession p) {
        em.merge(p);
        em.flush();
        return p;
    }

    //удаление данных
    @Transactional
    public void deleteProfession(Long professionID) {
        Profession w = this.getProfessionById(professionID);
        em.remove(w);
        em.flush();
    }

    public int getCountProfessions() {
        Number number = (Number) em.createQuery(" select count(name) from Profession ").getResultList().get(0);
        return number.intValue();
    }

    public List<Profession> getProfessions() {
        Query query = em.createQuery(" select p from Profession p ");
        List<Profession> listProfessions = query.getResultList();
        return listProfessions;
    }

    public List<Profession> getProfessionsPage(int page) {
        Query query = em.createQuery(" select p from Profession p ");
        query.setFirstResult((page-1)*4);
        query.setMaxResults(4);

        List<Profession> listProfessions = query.getResultList();
        return listProfessions;
    }

    public Profession getProfessionById(Long professionID) {
        Profession p = em.find(Profession.class, professionID);
        return p;
    }

    //------------------------------------------------------------------------------------------------------------------
    //MedicalOrganization
    //------------------------------------------------------------------------------------------------------------------
//вставка данных
    @Transactional
    public MedicalOrganization insertMedicalOrganization(MedicalOrganization m) {
        em.merge(m);
        em.flush();
        return m;
    }

    //обновление данных
    @Transactional
    public MedicalOrganization updateMedicalOrganization(MedicalOrganization p) {
        em.merge(p);
        em.flush();
        return p;
    }

    //удаление данных
    @Transactional
    public void deleteMedicalOrganization(Long medOrgID) {
        MedicalOrganization m = this.getMedicalOrganizationById(medOrgID);
        em.remove(m);
        em.flush();
    }

    public int getCountMedicalOrganization() {
        Number number = (Number) em.createQuery(" select count(name) from MedicalOrganization ").getResultList().get(0);
        return number.intValue();
    }

    public List<MedicalOrganization> getMedicalOrganizations() {
        Query query = em.createQuery(" select m from MedicalOrganization m ");
        List<MedicalOrganization> listMedicalOrganization = query.getResultList();
        return listMedicalOrganization;
    }

    public List<MedicalOrganization> getMedicalOrganizationPage(int page) {
        Query query = em.createQuery(" select p from MedicalOrganization p ");
        query.setFirstResult((page-1)*4);
        query.setMaxResults(4);

        List<MedicalOrganization> listMedicalOrganization = query.getResultList();
        return listMedicalOrganization;
    }

    public MedicalOrganization getMedicalOrganizationById(Long medicalOrganizationID) {
        MedicalOrganization m = em.find(MedicalOrganization.class, medicalOrganizationID);
        return m;
    }
    //------------------------------------------------------------------------------------------------------------------
    //MarksTimesheet
    //------------------------------------------------------------------------------------------------------------------
    @Transactional
    public MarksTimesheet insertMarksTimesheet(MarksTimesheet m) {
        em.merge(m);
        em.flush();
        return m;
    }

    //обновление данных
    @Transactional
    public MarksTimesheet updateMarksTimesheet(MarksTimesheet m) {
        em.merge(m);
        em.flush();
        return m;
    }

    //удаление данных
    @Transactional
    public void deleteMarksTimesheet(Long marksTimesheetID) {
        MarksTimesheet w = this.getMarksTimesheetById(marksTimesheetID);
        em.remove(w);
        em.flush();
    }

    public int getCountMarksTimesheet() {
        Number number = (Number) em.createQuery(" select count(name) from MarksTimesheet ").getResultList().get(0);
        return number.intValue();
    }

    public List<MarksTimesheet> getMarksTimesheet() {
        Query query = em.createQuery(" select p from MarksTimesheet p ");
        List<MarksTimesheet> listMarksTimesheet = query.getResultList();
        return listMarksTimesheet;
    }

    public List<MarksTimesheet> getMarksTimesheetPage(int page) {
        Query query = em.createQuery(" select p from MarksTimesheet p ");
        query.setFirstResult((page-1)*4);
        query.setMaxResults(4);

        List<MarksTimesheet> listMarksTimesheet = query.getResultList();
        return listMarksTimesheet;
    }

    public MarksTimesheet getMarksTimesheetById(Long marksTimesheetID) {
        MarksTimesheet m = em.find(MarksTimesheet.class, marksTimesheetID);
        return m;
    }
    //------------------------------------------------------------------------------------------------------------------
    //OperatingMode
    //------------------------------------------------------------------------------------------------------------------
//вставка данных
    @Transactional
    public OperatingMode insertOperatingMode(OperatingMode o) {
        em.merge(o);
        em.flush();
        return o;
    }

    //обновление данных
    @Transactional
    public OperatingMode updateOperatingMode(OperatingMode o) {
        em.merge(o);
        em.flush();
        return o;
    }

    //удаление данных
    @Transactional
    public void deleteOperatingMode(Long ID) {
        OperatingMode o = this.getOperatingModeById(ID);
        em.remove(o);
        em.flush();
    }

    public int getCountOperatingMode() {
        Number number = (Number) em.createQuery(" select count(mark) from OperatingMode ").getResultList().get(0);
        return number.intValue();
    }

    public List<OperatingMode> getOperatingMode() {
        Query query = em.createQuery(" select p from OperatingMode p ");
        List<OperatingMode> listOperatingMode = query.getResultList();
        return listOperatingMode;
    }

    public List<OperatingMode> getOperatingModePage(int page) {
        Query query = em.createQuery(" select p from OperatingMode p ");
        query.setFirstResult((page-1)*4);
        query.setMaxResults(4);

        List<OperatingMode> listOperatingMode = query.getResultList();
        return listOperatingMode;
    }

    public OperatingMode getOperatingModeById(Long ID) {
        OperatingMode o = em.find(OperatingMode.class, ID);
        return o;
    }
    //------------------------------------------------------------------------------------------------------------------
    //TypeOfDisability
    //------------------------------------------------------------------------------------------------------------------
//вставка данных
    @Transactional
    public TypeOfDisability insertTypeOfDisability(TypeOfDisability t) {
        em.merge(t);
        em.flush();
        return t;
    }

    //обновление данных
    @Transactional
    public TypeOfDisability updateTypeOfDisability(TypeOfDisability t) {
        em.merge(t);
        em.flush();
        return t;
    }

    //удаление данных
    @Transactional
    public void deleteTypeOfDisability(Long ID) {
        TypeOfDisability o = this.getTypeOfDisabilityById(ID);
        em.remove(o);
        em.flush();
    }

    public int getCountTypeOfDisability() {
        Number number = (Number) em.createQuery(" select count(name) from TypeOfDisability ").getResultList().get(0);
        return number.intValue();
    }

    public List<TypeOfDisability> getTypeOfDisability() {
        Query query = em.createQuery(" select p from TypeOfDisability p ");
        List<TypeOfDisability> listTypeOfDisability = query.getResultList();
        return listTypeOfDisability;
    }

    public List<TypeOfDisability> getTypeOfDisabilityPage(int page) {
        Query query = em.createQuery(" select p from TypeOfDisability p ");
        query.setFirstResult((page-1)*4);
        query.setMaxResults(4);

        List<TypeOfDisability> listTypeOfDisability = query.getResultList();
        return listTypeOfDisability;
    }

    public TypeOfDisability getTypeOfDisabilityById(Long ID) {
        TypeOfDisability o = em.find(TypeOfDisability.class, ID);
        return o;
    }

}
