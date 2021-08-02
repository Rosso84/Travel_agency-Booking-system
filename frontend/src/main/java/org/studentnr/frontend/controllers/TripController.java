package org.studentnr.frontend.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.studentnr.backend.entities.Trip;
import org.studentnr.backend.service.TripService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
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

    private Trip selectedTrip;


    public boolean isTripSelected() {
        return getSelectedTrip() != null;
    }

    public List<Trip> getTopNTripsList(){
        topNTripsList = tripService.getTop_N_Trips( numberOfTopTrips );
        return topNTripsList;
    }

    public void selectTrip(Long id){
        System.out.println( "selectedTripId:" + id);

        selectedTrip = tripService.getTrip(id);
        System.out.println("selectedTrip:" + selectedTrip.getLocation());

        setSelectedTrip( selectedTrip );
    }


    public void setSelectedTrip(Trip selectedTrip) {
        this.selectedTrip = selectedTrip;
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


    public void setTopNTripsList( List<Trip> topNTripsList ) {
        this.topNTripsList = topNTripsList;
    }


    public List<Trip> getTripsByLocationList() {
        return this.tripsByLocationList;
    }


    public void retrieveTripsByLocation( String location ) {
         this.tripsByLocationList = tripService.getByTripLocationOrderByCostAscending( location.toLowerCase() );
    }


    public String getLocation() {
        return location;
    }


    public void setLocation(String location) {
        this.location = location;
    }

}
