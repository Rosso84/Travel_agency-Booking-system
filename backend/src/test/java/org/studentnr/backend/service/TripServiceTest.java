package org.studentnr.backend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.studentnr.backend.StubApplication;
import org.studentnr.backend.entities.Trip;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = StubApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class TripServiceTest extends ServiceTestBase{



    @Autowired
    private TripService tripService;

    private ValidatorFactory valFactory;
    private Validator validator;

    LocalDate dateOfToday = LocalDate.now();
    LocalDate departureDate= dateOfToday.plusYears(1).plusMonths(1);
    LocalDate returnDate= departureDate.plusWeeks(3);

    @BeforeEach
    public void init() {
        valFactory = Validation.buildDefaultValidatorFactory();
        validator = valFactory.getValidator();
    }

    private <T> boolean hasViolations(T obj){
        Set<ConstraintViolation<T>> violations = validator.validate(obj);

        for(ConstraintViolation<T> cv : violations){
            System.out.println("VIOLATION: "+cv.toString());
        }

        return violations.size() > 0;
    }

    private Long createValidTrip(String location, Integer cost){
        String title = "Some title";
        String discription = "Discription about the trip. It is a nice trip. All included";

        Long tripId = tripService.createTrip( title, discription, cost, location, departureDate, returnDate );
        return tripId;
    }


    private void createMultipleTrips(){
        Long thailand = createValidTrip("Thailand",30000);
        Long thailand3 = createValidTrip("Thailand",30000);
        Long thailand2 = createValidTrip("Thailand",30000);
        Long spain = createValidTrip("Spain",40000);
        Long spain2 = createValidTrip("Spain",40000);
        Long spain3 = createValidTrip("Spain",40000);
        Long russia = createValidTrip("Russia",50000);
        Long russia2 = createValidTrip("Russia",50000);
        Long pakistan = createValidTrip("Pakistan", 1500);
        Long pakistan2 = createValidTrip("Pakistan", 1500);
        Long pakistan3 = createValidTrip("Pakistan", 1500);
        Long somalia = createValidTrip("Somalia",1000);
    }



    @Test
    public void testNoTrips(){
        List<Trip> trips = tripService.getAllTripsOrderByCostAscending();
        assertEquals(0, trips.size());
    }


    @Test
    public void testCreateTrip(){
        Long tripId = createValidTrip("Russia", 10000);
        assertNotNull(tripId);
    }

    @Test
    public void testGetTripById(){
        Long tripID = createValidTrip("Thailand", 10000);
        Trip trip = tripService.getTrip(tripID);
        assertEquals(tripID, trip.getId());
    }

    @Test
    public void testGetAllTripsOrderByCostAscending(){
        createMultipleTrips();
        List<Trip> tripList = tripService.getAllTripsOrderByCostAscending();

        assertEquals(12, tripList.size());

        assertEquals("Somalia", tripList.get(0).getLocation() ); //cost=1000
        assertEquals("Pakistan", tripList.get(1).getLocation() );//cost=1500 .
        assertEquals("Pakistan", tripList.get(2).getLocation() );
        assertEquals("Pakistan", tripList.get(3).getLocation() );
        assertEquals("Thailand", tripList.get(4).getLocation() );//cost=30000
        assertEquals("Thailand", tripList.get(5).getLocation() );
        assertEquals("Thailand", tripList.get(6).getLocation() );
    }

    @Test
    void testGetByTripLocationOrderByCostAscending() {
        createMultipleTrips();

        String thailand = "Thailand";
        String spain = "Spain";

        List< Trip > tripsByLocationThai = tripService.getByTripLocationOrderByCostAscending( thailand );
        List< Trip > tripsByLocationSpain = tripService.getByTripLocationOrderByCostAscending( spain );

        assertEquals(3, tripsByLocationSpain.size() );
        assertEquals(3, tripsByLocationThai.size() );
    }

    @Test
    void testGetByCostOrderByCostAscending() {
        createMultipleTrips();

        List<Trip> tripsByCost = tripService.getByCostOrderByCostAscending( 10 );
        assertEquals(0, tripsByCost.size() );

        tripsByCost = tripService.getByCostOrderByCostAscending( 30000 );

        assertEquals(3, tripsByCost.size()); //should be 3 trips to Thailand..
    }

    @Test
    void getByDepartureDateOrderByCostAscending() {
        createMultipleTrips();

        List< Trip > noTrips = tripService.getByDepartureDateOrderByCostAscending( dateOfToday );
        assertEquals(0, noTrips.size() );

        List<Trip> existingTrips = tripService.getByDepartureDateOrderByCostAscending( departureDate );
        assertEquals(12, existingTrips.size() );

    }

    @Test
    void testDeleteTrip() {
        String location = "Danmark";
        Long tripId = createValidTrip(location, 4000);
        assertNotNull( tripId );

        Trip trip = tripService.getTrip( tripId );

        assertEquals( location, trip.getLocation() );
        assertTrue( tripService.deleteTrip( tripId ) );
        assertNull( tripService.getTrip( tripId ) );
    }

    @Test
    void getTop_N_Trips() {
        createMultipleTrips();
         List<Trip> trips = tripService.getTop_N_Trips( 5 );

         assertEquals(5, trips.size());
         assertTrue( trips.get( 0 ).getCost() == 1000 ); //Somalia
         assertTrue( trips.get( 1 ).getCost() == 1500 ); //Pakistan
         assertTrue( trips.get( 2 ).getCost() == 1500 ); //Pakistan
         assertTrue( trips.get( 3 ).getCost() == 1500 ); //Pakistan
         assertTrue( trips.get( 4 ).getCost() == 30000 ); //Thailand
    }

    @Test
    public void testSetWrongFutureDepartureDateFails(){
        String title = "Some title";
        String discription = "Discription about the trip. It is a nice trip. All included";

        Trip trip = new Trip();
        trip.setTitle( title );
        trip.setDiscription( discription );
        trip.setCost(10000);
        trip.setDepartureDate( dateOfToday ); //Should not work as its set to @Future. date must be after today
        trip.setReturnDate( returnDate );

        assertTrue( hasViolations( trip ) );

        trip.setDepartureDate( departureDate );

    }
}
