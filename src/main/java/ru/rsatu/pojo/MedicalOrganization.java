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
public class MedicalOrganization extends PanacheEntity {
    private String name;
    private String contacts;
    private String address;
    @OneToMany(mappedBy = "medicalOrganization", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.EAGER)
    //@OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    public List<SickLeaves> sickLeave;

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getContacts() {
        return contacts;
    }
    public void setContacts(String contacts) {
        this.contacts = contacts;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }


    @Override
    public String toString() {
        return "MedicalOrganization{" +
                "medicalOrganizationID='" + id + '\'' +
                ", name=" + name +
                ", contacts=" + contacts +
                ", address=" + address +
                ", sickLeave=" + sickLeave +
                '}';
    }
}
