package org.studentnr.backend.entities;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

//@Entity
public class Trip {

    @Id @GeneratedValue
    private Long id;

    @NotBlank
    @Size(max=255)
    private String title;

    //@NotBlank //TODO: Can be blank???
    private String description;

    //@Min(0) TODO: remember to add 'check (cost>0)' to flyway to avoid using negative values
    @NotNull
    private int cost;

    @NotBlank
    @Size(max = 128)
    private String location;

    //@Future  // Cannot be in past..there is a @Past constraint as well (Can be used for registerDate.)
    @NotNull
    private String departureDate;

    //@Future  //TODO: remember to check if provided returnDate is before departure and test if past fails..
    @NotNull
    private String returnDate;

    /* Need also flightEntity, seatEntity, TripDetailsEnt etc, but not for now.. */
    //@NotNull
    //private Flight flight;



    //Need an empty constructor
    public Trip(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }



}
