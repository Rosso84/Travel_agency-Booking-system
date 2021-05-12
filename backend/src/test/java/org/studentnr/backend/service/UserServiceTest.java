package org.studentnr.backend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.studentnr.backend.StubApplication;
import org.studentnr.backend.entities.Purchase;
import org.studentnr.backend.entities.Trip;
import org.studentnr.backend.entities.User;

import javax.persistence.Persistence;
import javax.validation.*;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = StubApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class UserServiceTest extends ServiceTestBase{


    @Autowired
    private UserService userService;

    @Autowired
    private TripService tripService;

    private ValidatorFactory valFactory;
    private Validator validator;

    String email = "Rosso@Hotmail.com";
    String name = "Rosso";
    String midleName = "Melodi";
    String surename = "Merandi";
    String address = "someAdress 99";
    String postalCode = "33rd street";
    String password = "MyPassword";

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

    public User createValidUser(String email){
        User user = new User();
        user.setEmail(email);
        user.setFirstname(name);
        user.setMiddleName(midleName);
        user.setSurname(surename);
        user.setAddress(address);
        user.setPostalCode(postalCode);
        user.setPassword(password);
        user.setEnabled(true);

        return user;
    }

    private Long createValidTrip(String location){
        String title = "Family trip";
        String discription = "Discription about the trip. It is a nice trip. All included";
        Integer cost = 12000;
        LocalDate depDate = LocalDate.of(2021, 8, 15);
        LocalDate retDate = LocalDate.of(2021, 8, 29);
        Long tripId = tripService.createTrip(title,discription, cost, location, depDate, retDate);
        return tripId;
    }

    @Test
    public void testCreateUser() {

        boolean created = userService.createUser(email, name, midleName, surename, address, postalCode, password);

        assertTrue(created);

        User user = userService.getUser(email, false);

        assertEquals(email, user.getEmail());
        assertThrows(Exception.class, () -> user.getPurchases().size());


    }


    @Test
    public void testCreateTwice(){
        boolean created = userService.createUser(email, name, midleName, surename, address, postalCode, password);
        assertTrue(created);

        boolean reCreated = userService.createUser(email, name, midleName, surename, address, postalCode, password);
        assertFalse(reCreated);

    }

    @Test
    public void testTooShortEmail(){
        String myEMail = "x@f.n";

        User user = createValidUser(myEMail);

        assertTrue( hasViolations( user ) );


    }

    @Test
    public void testTooLongEmail(){
        String x = "";
        for(int i = 0; i < 255; i++){
            x = x.concat("a");
        }

        String email = "Rossi";
        email = email.concat(x);
        email = email.concat("@Gmail.com");
        User user = createValidUser(email);

        assertTrue( hasViolations( user ) );
    }



    @Test
    public void testPasswordTooLong(){
        //Same principles here..
    }

    @Test
    public void testBookTrip(){
        boolean createdUser = userService.createUser(email, name, midleName, surename, address, postalCode, password);
        User user = userService.getUser(email, true);

        Long tripId = createValidTrip("Thailand");
        assertNotNull( tripId );

        Long purchase_id = userService.bookTrip(user.getEmail(), tripId);
        Purchase bookedTrip = userService.getBookedTrip(purchase_id);

        assertNotNull(purchase_id);
        assertEquals(user.getEmail(), bookedTrip.getUser().getEmail());
    }

    @Test
    public void testGetWithPurchases(){
        //TODO: finish after testing PurchaseServices
    }












}
