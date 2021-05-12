package org.studentnr.backend.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.studentnr.backend.entities.Trip;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.function.Supplier;

@Service
public class DefaultInitializerService {

    @Autowired
    private UserService userService;

    @Autowired
    private TripService tripService;


    @PostConstruct
    public void initialize(){

        String userId = "CharlesSobraj@gmail.com";
        attempt(() -> userService.createUser( userId,"Charles", "holmes", "Sobraj",
                "OclahomaStreet 22nd", "2020", "AbCDEfGKLFOOO"));


        LocalDate dateOfToday = LocalDate.now();
        LocalDate depDate= dateOfToday.plusYears(1).plusMonths(1);
        LocalDate returnDate= depDate.plusWeeks(3);

        attempt(() -> tripService.createTrip("Trip to Thailand", "Perfect for family and very safe with many activities",
                30000, "thailand", depDate, returnDate));

        LocalDate depDate2 = depDate.plusMonths(1);
        LocalDate returnDate2 = depDate.plusWeeks(3);
        attempt(() -> tripService.createTrip("Trip to Egypt", "Perfect for couple and very exciting",
                1200, "egypt", depDate2, returnDate2));

        LocalDate depDate3 = depDate2.plusMonths(1);
        LocalDate returnDate3 = depDate2.plusWeeks(3);
        attempt(() -> tripService.createTrip("Trip to Bali", "Perfect for singles and a lot of alcohol involved",
                6000, "bali", depDate3, returnDate3));

        LocalDate depDate4 = depDate3.plusMonths(1);
        LocalDate returnDate4 = depDate3.plusWeeks(3);
        attempt(() -> tripService.createTrip("Trip to Spain", "Perfect for family and very safe with many activities",
                30000, "Spain", depDate4, returnDate4));

        LocalDate depDate5 = depDate4.plusMonths(1);
        LocalDate returnDate5 = depDate4.plusWeeks(3);
        attempt(() -> tripService.createTrip("Trip to Vietnam", "Perfect for couple and very exciting",
                1200, "vietnam", depDate5, returnDate5));

        LocalDate depDate6 = depDate5.plusMonths(1);
        LocalDate returnDate6 = depDate5.plusWeeks(3);
        attempt(() -> tripService.createTrip("Trip to Mexico", "Exciting, but risky. Not just alcohol involved",
                7000, "mexico", depDate6, returnDate6));


        //TODO: remove if not needed
       /* attempt(() -> userService.bookTrip(userId, thaiTrip));
        attempt(() -> userService.bookTrip(userId, egyptTrip));
        attempt(() -> userService.bookTrip(userId, baliTrip));
        attempt(() -> userService.bookTrip(userId, spainTrip));
        attempt(() -> userService.bookTrip(userId, vietnamTrip));
        attempt(() -> userService.bookTrip(userId, mexicoTrip));*/

    }



    private  <T> T attempt(Supplier<T> lambda){
        try{
            return lambda.get();
        }catch (Exception e){
            return null;
        }
    }
}
