package org.studentnr.backend.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.studentnr.backend.StubApplication;
import org.studentnr.backend.entities.Purchase;
import org.studentnr.backend.entities.Trip;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_CLASS;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = StubApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
//Important, as referring to state given by Singleton that
//could be modified by previous tests, as they share the same
//SpringBoot application context (ie SpringBoot is started only
//once for all tests).
//Also note that this class does NOT extend ServiceTestBase
@DirtiesContext(classMode = BEFORE_CLASS)
public class DefaultDataInitializerServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private TripService tripService;


    @Test
    public void testInit() {

        assertTrue(userService.getAllUsers(false).size() > 0);

        assertTrue(userService.getAllUsers(true).stream()
                .mapToLong(c -> c.getPurchases().size())
                .sum() > 0);

        assertTrue(tripService.getAllTripsOrderByCostAscending().size() > 0);
    }
}
