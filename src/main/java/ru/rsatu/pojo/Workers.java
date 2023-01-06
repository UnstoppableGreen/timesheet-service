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
public class Workers extends PanacheEntity {
    private Long serviceNumber; //табельный номер
    private String name;
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    public Profession profession;
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    public Divisions division;
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    public OperatingMode operatingMode;
    private String contacts;
    private String address;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;


    @OneToMany(targetEntity=ru.rsatu.pojo.SickLeaves.class, mappedBy = "worker", cascade = CascadeType.ALL, orphanRemoval = false)
    //@OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    public List<SickLeaves> sickLeave;
    @OneToMany(targetEntity=ru.rsatu.pojo.Vacations.class,mappedBy = "worker", cascade = CascadeType.ALL, orphanRemoval = false)
    //@OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    public List<Vacations> vacations;
    @OneToMany(targetEntity=ru.rsatu.pojo.BusinessTrip.class,mappedBy = "worker", cascade = CascadeType.ALL, orphanRemoval = false)
    //@OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    public List<BusinessTrip> businessTrip;


    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getServiceNumber() {
        return serviceNumber;
    }
    public void setServiceNumber(Long serviceNumber) {
        this.serviceNumber = serviceNumber;
    }
    public String getContacts() {
        return contacts;
    }
    public void setContacts(String contacts) {
        this.contacts = contacts;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "workerID='" + id + '\'' +
                ", serviceNumber='" + serviceNumber + '\'' +
                ", name=" + name +
                ", profession=" + profession +
                ", division=" + division +
                ", operatingMode=" + operatingMode +
                ", contacts=" + contacts +
                ", address=" + address +
                ", date=" + date +
                '}';
    }
}
