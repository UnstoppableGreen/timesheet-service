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
public class BusinessTrip extends PanacheEntity {
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    public Workers worker;

    private String destination;
    
    private String numberCommand;
        
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateCommand;
    
    private String tripPurpose;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date beginDate;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date endDate;

    public Long getId() {
        return id;
    }


    public String getDestination() {
		return destination;
	}


	public String getNumberCommand() {
		return numberCommand;
	}


	public void setNumberCommand(String numberCommand) {
		this.numberCommand = numberCommand;
	}


	public void setDestination(String destination) {
		this.destination = destination;
	}


	public String getTripPurpose() {
		return tripPurpose;
	}


	public void setTripPurpose(String tripPurpose) {
		this.tripPurpose = tripPurpose;
	}


	@Override
    public String toString() {
        return "BusinessTrip{" +
                "BusinessTripID='" + id + '\'' +
                ", numberCommand=" + numberCommand +
                ", dateCommand=" + dateCommand +
                ", destination=" + destination +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", worker=" + worker +
                ", tripPurpose=" + tripPurpose +
                '}';
    }
}
