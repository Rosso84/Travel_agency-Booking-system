package org.studentnr.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.studentnr.backend.entities.Purchase;
import org.studentnr.backend.service.PurchaseService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    public void bookTrip(String userId_email, Long tripId){
        Long purchaseId = purchaseService.bookTrip( userId_email, tripId );

        TripController tc = new TripController();
        tc.setSelectedTrip(null);

        System.out.println("bookTrip method selected. with purchaseID: " + purchaseId);
    }

    public Purchase getBookedTrip(long purchaseId){
        return purchaseService.getBookedTrip( purchaseId );
    }
}
