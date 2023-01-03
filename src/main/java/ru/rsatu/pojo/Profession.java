package ru.rsatu.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Profession extends PanacheEntity {

    private String name;

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @OneToMany(mappedBy = "profession", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    //@OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    public List<Workers> workers;

    @Override
    public String toString() {
        return "Professions{" +
                "professionID='" + id + '\'' +
                ", name=" + name +
                ", workers=" + workers +
                '}';
    }
}
