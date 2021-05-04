package org.studentnr.backend.entities;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
public class Trip {

    @Id @GeneratedValue
    private Long id;

    @NotBlank
    @Size(min=1, max=500)
    private String title;

    //@NotBlank //TODO: Can be blank???
    @Size(max=5000)
    private String description;

    @Min(0) @NotNull
    private int cost;

    @NotBlank
    @Size(min=2, max = 124)
    private String location;

    @Future  // Cannot be in past..there is a @Past constraint as well (Can be used for registerDate.)
    @NotNull
    private LocalDate departureDate;

    @Future  //TODO: remember to check if provided returnDate is before departure and test if past fails..
    @NotNull
    private LocalDate returnDate;

    @NotNull
    @OneToOne
    private Purchase purchase; //parent

    /* Need also flightEntity, seatEntity TripDetailsEnt etc, but not for now.. */
    //@NotNull
    //private Flight flight;




    public Trip(){

    }

    public Purchase getPurchases() {
        return purchase;
    }

    public void setPurchases(Purchase purchase) {
        this.purchase = purchase;
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
