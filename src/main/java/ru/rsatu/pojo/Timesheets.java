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
public class Timesheets extends PanacheEntity {

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    public MarksTimesheet marksTimesheet;
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    public Workers workers;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Date entryDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Date exitDate;

    public Date getEntryDate() {
        return entryDate;
    }
    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }
    public Date getExitDate() {
        return exitDate;
    }
    public void setExitDate(Date exitDate) {
        this.exitDate = exitDate;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Timesheets{" +
                "timesheetID='" + id + '\'' +
                ", marksTimesheet=" + marksTimesheet +
                ", workers=" + workers +
                ", entryDate=" + entryDate +
                ", exitDate=" + exitDate +
                '}';
    }
}
