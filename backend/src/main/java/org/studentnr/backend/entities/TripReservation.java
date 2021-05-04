package org.studentnr.backend.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;


//@Entity TODO: Remove if not needed?
public class TripReservation {

    @Id @GeneratedValue
    private Long id;

    @NotNull
    @ManyToOne
    private Purchase parent;


    @NotNull
    //private User UserId;

    @Past //TODO: setting @Past ok for this??
    @NotNull
    private LocalDate bookedDate;


    public TripReservation(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getBookedDate() {
        return bookedDate;
    }

    public void setBookedDate(LocalDate bookedDate) {
        this.bookedDate = bookedDate;
    }

}
