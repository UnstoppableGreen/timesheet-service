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
public class TypeOfDisability extends PanacheEntity {
    private String name;
    @OneToMany(mappedBy = "typeOfDisability", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
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


    @Override
    public String toString() {
        return "TypeOfDisability{" +
                "typeOfDisabilityID='" + id + '\'' +
                ", name=" + name +
                ", sickLeave=" + sickLeave +
                '}';
    }
}
