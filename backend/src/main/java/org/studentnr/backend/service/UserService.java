package org.studentnr.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.studentnr.backend.entities.Purchase;
import org.studentnr.backend.entities.Trip;
import org.studentnr.backend.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

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


    public User getUser(String email_id, boolean with_purchases){
        User user = em.find(User.class, email_id);

            if(with_purchases && user != null){
            user.getPurchases().size();
        }
        return user;
    }


    public List<User> getAllUsers(boolean with_purchases){
        TypedQuery<User> query = em.createQuery("select c from User c", User.class);
        List<User> users = query.getResultList();

        if(with_purchases){
            //force loading
            users.forEach(c -> c.getPurchases().size());
        }

        return users;
    }

    public Long bookTrip(String userId, Long tripId) {
        User user = em.find(User.class, userId);
        Trip trip = em.find(Trip.class, tripId);

        if (user == null) {
            throw new IllegalArgumentException("User with id " + userId + " does not exist");
        }

        if (trip == null) {
            throw new IllegalArgumentException("Trip with id " + tripId + " does not exist");
        }

        LocalDate todaysDate = LocalDate.now();

        Purchase purchase = new Purchase();
        purchase.setUser( user );
        purchase.setTrip( trip );
        purchase.setBookedDate( todaysDate );
        em.persist( purchase );

        user.getPurchases().add( purchase );

        return purchase.getId();
    }

    public Purchase getBookedTrip(long id){
        return em.find(Purchase.class, id);
    }


}
