package org.studentnr.frontend.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.studentnr.backend.entities.Trip;
import org.studentnr.backend.service.TripService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class TripController implements Serializable {


    @Autowired
    private TripService tripService;

    private List<Trip> topNTripsList;

    private List<Trip> tripsByLocationList;

    private String location;

    private Integer numberOfTopTrips = 5;

    private Trip selectedTrip;


    public boolean isLocationNull() {
        return getLocation() == null;
    }

    public boolean isLocationBlank() {
        return getLocation().isBlank();
    }

    public List<Trip> getTopNTripsList() {
        if (this.topNTripsList == null) {
            topNTripsList = new ArrayList<>();
        }

        topNTripsList = tripService.getTop_N_Trips(numberOfTopTrips);

        return topNTripsList;
    }


    public String toDetailPage(Trip trip) {
        setSelectedTrip( trip );
        return "/tripDetail.jsf&faces-redirect=true";
    }


    public void setSelectedTrip(Trip trip) {
        this.selectedTrip = trip;
    }


    public Trip getSelectedTrip() {
        return selectedTrip;
    }


    public Integer getNumberOfTopTrips() {
        return numberOfTopTrips;
    }


    public void setNumberOfTopTrips(Integer numberOfTopTrips) {
        this.numberOfTopTrips = numberOfTopTrips;
    }


    public void retrieveTripsByLocationSearch() {
        this.tripsByLocationList = tripService.getByTripLocationOrderByCostAscending( getLocation().toLowerCase());
    }


    public List<Trip> getTripsByLocationList() {
        return this.tripsByLocationList;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
