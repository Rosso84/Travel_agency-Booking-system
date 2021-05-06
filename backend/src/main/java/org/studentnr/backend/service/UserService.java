package org.studentnr.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.studentnr.backend.entities.User;

import javax.persistence.EntityManager;
import java.util.Collections;

@Service
@Transactional
public class UserService {


    @Autowired
    private EntityManager em;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public boolean createUser(String email, String firstName, String midleName, String sureName,
                              String address, String postalCode, String password) {

        String hashedPassword = passwordEncoder.encode(password);

        if (em.find(User.class, email) != null) {
            return false;
        }

        User user = new User();
        user.setEmail(email);
        user.setFirstname(firstName);
        user.setMiddleName(midleName);
        user.setSurname(sureName);
        user.setAddress(address);
        user.setPostalCode(postalCode);
        user.setPassword(hashedPassword);

        user.setRoles(Collections.singleton("USER"));
        user.setEnabled(true);

        em.persist(user);

        return true;
    }

    public User getUser(String id){
        return em.find(User.class, id);
    }

    public boolean bookTrip(User user, String date){


        return true;
    }

}
