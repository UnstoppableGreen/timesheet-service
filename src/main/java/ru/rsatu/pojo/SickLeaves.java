package ru.rsatu.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class SickLeaves extends PanacheEntity {
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    public Workers worker;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    public MedicalOrganization medicalOrganization;

    private String number;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date issueDate;
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    public TypeOfDisability typeOfDisability;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date beginDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date endDate;

    public Long getId() {
        return id;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }


    @Override
    public String toString() {
        return "SickLeave{" +
                "sickLeaveID='" + id + '\'' +
                ", number=" + number +
                ", worker=" + worker +
                ", medicalOrganization=" + medicalOrganization +
                ", typeOfDisability=" + typeOfDisability +
                ", issueDate=" + issueDate +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                '}';
    }
}
