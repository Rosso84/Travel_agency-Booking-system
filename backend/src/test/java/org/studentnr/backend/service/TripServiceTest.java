package org.studentnr.backend.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.studentnr.backend.StubApplication;
import org.studentnr.backend.entities.Trip;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = StubApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class TripServiceTest  extends ServiceTestBase{

    @Autowired
    private TripService tripService;


    @Test
    public void testCreateTrip(){
        Long tripId = createValidTrip("Russia");
        assertNotNull(tripId);
    }

    @Test
    public void testGetTripById(){
        Long tripID = createValidTrip("Thailand");
        Trip trip = tripService.getTrip(tripID);
        assertEquals(tripID, trip.getId());
    }

    @Test
    public void testGetAllTrips(){
        Long trip1 = createValidTrip("Thailand");
        Long trip2 = createValidTrip("Spain");
        Long trip3 = createValidTrip("Russia");

        List<Trip> tripList = tripService.getAllTrips();
        assertEquals(3, tripList.size());

        assertEquals(trip1, tripList.get(0).getId());
        assertEquals(trip2, tripList.get(1).getId());
        assertEquals(trip3, tripList.get(2).getId());
    }



    private Long createValidTrip(String location){
        String title = "Familytrip to Greek";
        String discription = "Discription about the trip. It is a nice trip. All included";
        Integer cost = 12000;

        LocalDate dateOfToday = LocalDate.now();
        LocalDate depDate= dateOfToday.plusYears(1).plusMonths(1);
        LocalDate retDate= depDate.plusWeeks(3);

        Long tripId = tripService.createTrip(title,discription, cost, location, depDate, retDate);
        return tripId;
    }


    @Test
    public void testDeleteTrip() {

    }

}
