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

    /*private Long selectedTripId = null;

    private Trip selectedTrip = null;*/

    private Trip chosenTrip;


   /* public boolean isTripSelected() {
        return selectedTripId != null;
    }

    public Trip getSelectedTrip() {
        return selectedTrip;
    }

    public void selectTrip(Long id){
        selectedTripId = id;

        selectedTrip = tripService.getTrip(selectedTripId);

    }*/

    public List<Trip> getTopNTripsList(){
        topNTripsList = tripService.getTop_N_Trips( numberOfTopTrips );
        return topNTripsList;
    }

    public Trip getChosenTrip() {
        if (this.chosenTrip == null){
            System.out.println("GetChosen, Chosen trip is null");
            return null;
        }
        System.out.println( "getChosenTrip, Chosen trip id: ".concat( chosenTrip.getId().toString() ) );
        return chosenTrip;
    }

/*    public void setChosenTrip(Long  id){

        System.out.println("retrieved id: ".concat( id.toString() ) );
        for (Trip  trip : topNTripsList) {

            if (trip.getId().equals( id ) ){
                this.chosenTrip = trip;
            }
        }
        System.out.println("setChosenTrip is set to: ".concat( this.chosenTrip.getId().toString() ) );
    }*/

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
        if ( getTripsByLocationList() == null){
            tripsByLocationList = new ArrayList<>();
            return true;
        }
        else if ( getTripsByLocationList().size() == 0) {
            return true;
        }

        return false;
    }

    public int getNumberOfTripsAvailable(){
        return tripsByLocationList.size();
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
