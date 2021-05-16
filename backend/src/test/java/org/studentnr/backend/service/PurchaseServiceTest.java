package org.studentnr.backend.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.studentnr.backend.StubApplication;
import org.studentnr.backend.entities.Purchase;
import org.studentnr.backend.entities.Trip;
import org.studentnr.backend.entities.User;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = StubApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class PurchaseServiceTest extends ServiceTestBase{


    /**
     * This test class is not needed as we allready test most of it in USerServiceTest
     * //TODO: remove if codecoverage is no different.
     * */

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private UserService userService;

    @Autowired
    private TripService tripService;

    private String email = "Rosso@Hotmail.com";
    private String name = "Rosso";
    private String midleName = "Melodi";
    private String surename = "Merandi";
    private String address = "someAdress 99";
    private String postalCode = "33rd street";
    private String password = "MyPassword";

    private Long createValidTrip(String location){
        String title = "Family trip";
        String discription = "Discription about the trip. It is a nice trip. All included";
        Integer cost = 12000;

        LocalDate dateOfToday = LocalDate.now();
        LocalDate departureDate= dateOfToday.plusYears(1).plusMonths(1);
        LocalDate returnDate= departureDate.plusWeeks(3);

        Long tripId = tripService.createTrip(title,discription, cost, location, departureDate, returnDate);
        return tripId;
    }

    private User createValidUser(String email) {
        boolean createdUser = userService.createUser(email, name, midleName, surename, address, postalCode, password);
        User user = userService.getUser(email, true);
        return user;
    }

    @Test
    public void testBookTrip(){

        User user = createValidUser( email );

        Long tripId = createValidTrip("Thailand");
        assertNotNull( tripId );

        Long purchase_id = purchaseService.bookTrip( user.getEmail(), tripId );
        Purchase bookedTrip = purchaseService.getBookedTrip( purchase_id );

        assertNotNull(purchase_id);
        assertEquals(user.getEmail(), bookedTrip.getUser().getEmail() );
    }

    @Test
    public void testBookMany(){

        User user = createValidUser( email );

        Long tripId = createValidTrip("Thailand");
        assertNotNull( tripId );

        Long purchase_id = purchaseService.bookTrip( user.getEmail(), tripId );
        Purchase bookedTrip = purchaseService.getBookedTrip( purchase_id );

        assertNotNull( purchase_id );
        assertEquals( user.getEmail(), bookedTrip.getUser().getEmail() );
    }


}
