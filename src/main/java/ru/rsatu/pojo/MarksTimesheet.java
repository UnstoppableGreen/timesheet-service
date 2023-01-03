package ru.rsatu.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class MarksTimesheet extends PanacheEntity {

    private String mark;
    private String description;

    public Long getId() {
        return id;
    }
    public String getMark() {
        return mark;
    }
    public void setMark(String mark) {
        this.mark = mark;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public String toString() {
        return "MarksTimesheet{" +
                "markTimesheetID='" + id + '\'' +
                ", mark=" + mark +
                ", description=" + description +
                '}';
    }
}
