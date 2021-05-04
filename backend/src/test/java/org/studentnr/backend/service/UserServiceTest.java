package org.studentnr.backend.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.studentnr.backend.StubApplication;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = StubApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class UserServiceTest extends ServiceTestBase{


    @Autowired
    private UserService userService;


    @Test
    public void testCreateUser() {

        String email = "Rosso@Hotmail.com";
        String name = "Rosso";
        String midleName = "Melodi";
        String surname = "Merandi";
        String address = "someAdress 99";
        String postalCode = "33rd street";
        String password = "MyPassword";

        boolean created = userService.createUser(email, name, midleName, surname, address, postalCode, password);

        assertTrue(created);

    }


}
