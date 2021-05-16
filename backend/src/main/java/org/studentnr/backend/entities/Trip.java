package org.studentnr.backend.entities;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
public class Trip {

    @Id @GeneratedValue
    private Long id;

    @NotBlank
    @Size(max=250)
    private String title;

    @Size(max=500)
    @NotNull
    private String description;

    //@Min(0) TODO: remember to add 'check (cost>0)' to flyway to avoid using negative values
    @NotNull
    private Integer cost;

    @NotBlank
    @Size(max = 150)
    private String location;  //TODO: might need an ENUM for Country

/*    @NotBlank
    @Size(max = 150)
    private String City;  //TODO: might need an ENUM for cities*/

    @NotNull
    @Future
    private LocalDate departureDate;

    @NotNull
    @Future  //TODO: remember to check if provided returnDate is before departure and test if past fails..
    private LocalDate returnDate;


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

    public void setDiscription(String description) {
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

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }



}
