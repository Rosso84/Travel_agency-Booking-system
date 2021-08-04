package org.studentnr.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.studentnr.backend.entities.Purchase;
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


   /*     if (userId_email == null || userId_email.length() == 0) {
            System.out.println("User email received is null or empty");
        } else if( tc.getSelectedTripId() == null){
            System.out.println("received email: " + userId_email);

            Long purId = purchaseService.bookTrip( userId_email, tc.getSelectedTrip().getId() );
        }*/



      /*

       System.out.println("selected TripID from bookTryp():  "+ id);
*/
       /* if (tc.getSelectedTrip().getId() == null){
            System.out.println("selected TripID from bookTryp() is null ");
        }else{
            Long purchaseId = purchaseService.bookTrip( userId_email, tc.getSelectedTrip().getId() );
        }
*/
       /* tc.setSelectedTrip(null);
        System.out.println("purchased Trip with purchaseID: " + purchaseId);
*/
        return "/index.jsf&faces-redirect=true";
    }

    public Purchase getBookedTrip(long purchaseId) {
        return purchaseService.getBookedTrip(purchaseId);
    }


}
