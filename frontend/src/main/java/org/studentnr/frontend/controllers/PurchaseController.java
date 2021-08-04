package org.studentnr.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.studentnr.backend.entities.Purchase;
import org.studentnr.backend.entities.Trip;
import org.studentnr.backend.service.PurchaseService;
import org.studentnr.backend.service.TripService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private TripController tc;



    public String bookTrip(String userId_email) {

        Long purId = purchaseService.bookTrip( userId_email, tc.getSelectedTrip().getId() );

        //tc.setLocation( null );

        return "/ui/receipt.jsf&faces-redirect=true";
        //return "/index.jsf&faces-redirect=true";
    }

    public Purchase getBookedTrip(long purchaseId) {
        return purchaseService.getBookedTrip( purchaseId );
    }


}
