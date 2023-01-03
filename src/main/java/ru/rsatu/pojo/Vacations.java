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
public class Vacations extends PanacheEntity {

    private String numberCommand;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date commandDate;
    
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    public Workers worker;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date beginDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date endDate;

    public Long getId() {
        return id;
    }
    public String getNumberCommand() {
        return numberCommand;
    }
    public void setNumberCommand(String numberCommand) {
        this.numberCommand = numberCommand;
    }


    @Override
    public String toString() {
        return "Vacations{" +
                "vacationsID='" + id + '\'' +
                ", numberCommand=" + numberCommand +
                ", commandDate=" + commandDate +
                ", worker=" + worker +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                '}';
    }
}
