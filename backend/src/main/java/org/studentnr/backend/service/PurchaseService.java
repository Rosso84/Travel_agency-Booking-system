package org.studentnr.backend.service;

import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.studentnr.backend.entities.Purchase;
import org.studentnr.backend.entities.Trip;
import org.studentnr.backend.entities.User;

import javax.persistence.EntityManager;
import java.time.LocalDate;

@Service
@Transactional
public class PurchaseService {

    @Autowired
    EntityManager em;



    //TODO: remove if not needed


    public Long bookTrip(String userId_email, Long tripId) {
        User user = em.find(User.class, userId_email);
        Trip trip = em.find(Trip.class, tripId);

        if (user == null) {
            throw new IllegalArgumentException("User with id " + userId_email + " does not exist");
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

