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
public class OperatingMode extends PanacheEntity {
    private String name;
    @OneToMany(mappedBy = "operatingMode", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    //@OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    public List<Workers> workers;

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
        return "OperatingMode{" +
                "operatingModeID='" + id + '\'' +
                ", name=" + name +
                ", workers=" + workers +
                '}';
    }
}
