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

    private Long selectedTripId;


    public boolean isTripSelected() {
        return getSelectedTrip() != null;
    }

    public List<Trip> getTopNTripsList() {
        if (this.topNTripsList == null) {
            topNTripsList = new ArrayList<>();
        }
        System.out.println("----------------------Size of list beefore: " + topNTripsList.size());
        //getTopNTripsList().clear();
        topNTripsList = tripService.getTop_N_Trips(numberOfTopTrips);
        System.out.println("----------------------Size of list after: " + topNTripsList.size());
        return topNTripsList;
    }

    public String selectTrip(Long id) {
        System.out.println("selectedTripId:" + id);

        Trip trip = tripService.getTrip(id);
        System.out.println("selectedTrip:" + trip.getLocation());
        Long id1 = trip.getId();

        setSelectedTripId( id1 );
        setSelectedTrip( trip );

        return "/tripDetail.jsf&faces-redirect=true";
    }


    public String toDetailPage(Long id) {

        for (Trip trip : topNTripsList) {
            if (trip.getId().equals(id)) {
                setSelectedTrip(trip);
            }
        }
        System.out.println("selectedTrip to Detailpage:" + getSelectedTrip().getId());

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


    public List<Trip> getTripsByLocationList() {
        return this.tripsByLocationList;
    }


    public void retrieveTripsByLocation(String location) {
        this.tripsByLocationList = tripService.getByTripLocationOrderByCostAscending(location.toLowerCase());
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setSelectedTripId(Long id) {
        this.selectedTripId = id;
    }

    public Long getSelectedTripId(){
        return this.selectedTripId;
    }

}
