package org.studentnr.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.studentnr.backend.entities.Trip;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class TripService {

    @Autowired
    EntityManager em;

    public Long createTrip(String title, String discription, Integer cost,
                           String location, LocalDate depDate, LocalDate retDate){

        Trip trip = new Trip();
        trip.setTitle(title);
        trip.setDiscription(discription);
        trip.setCost(cost);
        trip.setLocation(location);
        trip.setDepartureDate(depDate);
        trip.setReturnDate(retDate);
        em.persist(trip);

        return trip.getId();
    }


    public Trip getTrip(Long trip_id){
        Trip trip = em.find(Trip.class, trip_id);
        return trip;
    }


     public boolean deleteTrip(Long trip_id){
        Trip trip = em.find( Trip.class, trip_id );
        if ( trip == null ){
            throw new IllegalArgumentException("Trip with id " + trip + " does not exist");
        }
        em.remove( trip );
        return true;
     }


    //TODO: if with FLights and seats remember to get size
    public List<Trip> getAllTrips() {
        TypedQuery<Trip> query = em.createQuery("select c from Trip c", Trip.class);
        List<Trip> trips = query.getResultList();

        return trips;
    }

    public List<Trip> getByTripLocation(String location) {
        TypedQuery<Trip> query = em.createQuery("select c from Trip c where c.location = " +location+ " order by c.cost asc", Trip.class);
        List<Trip> trips = query.getResultList();

        return trips;
    }

    public List<Trip> getByCost(Integer cost) {
        TypedQuery<Trip> query = em.createQuery("select c from Trip c where c.cost = " +cost+ " order by c.cost asc", Trip.class);
        List<Trip> trips = query.getResultList();

        return trips;
    }

    public List<Trip> getByDepartureDate(LocalDate departureDate) {
        TypedQuery<Trip> query = em.createQuery("select c from Trip c where c.departureDate = " +departureDate+ " order by c.cost asc", Trip.class);
        List<Trip> trips = query.getResultList();

        return trips;
    }

}