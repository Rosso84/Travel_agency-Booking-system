package org.studentnr.backend.entities;

import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;


@Entity
public class Purchase {

    @Id @GeneratedValue
    private Long id;

    @NotNull
    @ManyToOne
    private User user;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private Trip trip;


    /*
    When you want to represent a date in Java, use classes from java.time.* package,
    like for example ZonedDateTime (Java 8) and not Date (Java <= 7).
    You can consider java.util.Date as deprecated.

    Note: if you are interested in just the date, and not the time,
    you can use LocalDate (like it would be for a birthday date).
    If you are only interested in the time,  use LocalTime.
    If need both and also time zone, use
    ZonedDateTime, because it contains all the following:

    - the date: eg 1/1/2017
    - the time: eg 16:43:23
    - the zone: eg CET (Central European Time) and UTC (Coordinated Universal Time)

    Note: JPA 2.1 (JEE 7) did not support Java 8 time objects, whereas Hibernate did.
    JPA 2.2 (in JEE 8) does support directly them.
    */
    @NotNull
    private LocalDate bookedDate;


    public Purchase(){

    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBookedDate(LocalDate bookedDate) {
        this.bookedDate = bookedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public LocalDate getBookedDate() {
        return bookedDate;
    }
}
