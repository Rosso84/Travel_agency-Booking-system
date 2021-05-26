package org.studentnr.frontend.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.studentnr.backend.entities.Trip;
import org.studentnr.backend.service.TripService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class TripController implements Serializable {



    @Autowired
    private TripService tripService;

    private List<Trip> topNTripsList;

    private List<Trip> tripsByLocationList;

    private String location;

    private Integer numberOfTopTrips = 5;


    public List<Trip> getTopNTripsList(){
        topNTripsList = tripService.getTop_N_Trips( numberOfTopTrips );
        return topNTripsList;
    }

    public Integer getNumberOfTopTrips() {
        return numberOfTopTrips;
    }

    public void setNumberOfTopTrips(Integer numberOfTopTrips) {
        this.numberOfTopTrips = numberOfTopTrips;
    }


    public void setTopNTripsList( List<Trip> topNTripsList ) {
        this.topNTripsList = topNTripsList;
    }


    public List<Trip> getTripsByLocationList() {
        return this.tripsByLocationList;
    }

    public boolean getTripsByLocationListIsEmptyOrNull(){
        return getTripsByLocationList().size() == 0 || getTripsByLocationList() == null;
    }


    public void retrieveTripsByLocation(String location) {
         this.tripsByLocationList = tripService.getByTripLocationOrderByCostAscending( location.toLowerCase() );
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


}
