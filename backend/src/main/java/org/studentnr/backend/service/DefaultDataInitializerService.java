package org.studentnr.backend.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.function.Supplier;

@Service
public class DefaultDataInitializerService {

    @Autowired
    private UserService userService;

    @Autowired
    private TripService tripService;

    @Autowired
    private PurchaseService purchaseService;



    @PostConstruct
    public void initialize(){

        String userId = "CharlesSobraj@gmail.com";
        attempt(() -> userService.createUser( userId,"Charles", "holmes", "Sobraj",
                "OclahomaStreet 22nd", "2020", "AbCDEfGKLFOOO"));


        LocalDate dateOfToday = LocalDate.now();
        LocalDate depDate= dateOfToday.plusYears(1).plusMonths(1);
        LocalDate returnDate= depDate.plusWeeks(3);

        Long franceTrip = attempt(() -> tripService.createTrip("Trip to France", "Perfect for family and very safe with many activities",
                30000, "france", depDate, returnDate));

        LocalDate depDate2 = depDate.plusMonths(1);
        LocalDate returnDate2 = depDate.plusWeeks(3);

        Long egyptTrip = attempt(() -> tripService.createTrip("Trip to Egypt", "Perfect for couple and very exciting",
                1200, "egypt", depDate2, returnDate2));

        LocalDate depDate3 = depDate2.plusMonths(1);
        LocalDate returnDate3 = depDate2.plusWeeks(3);

        Long baliTrip = attempt(() -> tripService.createTrip("Trip to Bali", "Perfect for singles and a lot of alcohol involved",
                6000, "bali", depDate3, returnDate3));

        LocalDate depDate4 = depDate3.plusMonths(1);
        LocalDate returnDate4 = depDate3.plusWeeks(3);

        Long israelTrip = attempt(() -> tripService.createTrip("Trip to Israel", "Perfect for family and very safe with many activities",
                30000, "israel", depDate4, returnDate4));

        LocalDate depDate5 = depDate4.plusMonths(1);
        LocalDate returnDate5 = depDate4.plusWeeks(3);

        Long vietnamTrip = attempt(() -> tripService.createTrip("Trip to Vietnam", "Perfect for couple and very exciting",
                1200, "vietnam", depDate5, returnDate5));

        LocalDate depDate6 = depDate5.plusMonths(1);
        LocalDate returnDate6 = depDate5.plusWeeks(3);

        Long mallorcaTrip = attempt(() -> tripService.createTrip("Trip to Mallorca", "Exciting, but risky. Not just alcohol involved",
                7000, "mallorca", depDate6, returnDate6));


        attempt( () -> purchaseService.bookTrip( userId, franceTrip ) );
        attempt( () -> purchaseService.bookTrip( userId, egyptTrip ) );
        attempt( () -> purchaseService.bookTrip( userId, baliTrip ) );
        attempt( () -> purchaseService.bookTrip( userId, israelTrip ) );
        attempt( () -> purchaseService.bookTrip( userId, vietnamTrip ) );
        attempt( () -> purchaseService.bookTrip( userId, mallorcaTrip ) );

    }



    private  <T> T attempt(Supplier<T> lambda){
        try{
            return lambda.get();
        }catch (Exception e){
            return null;
        }
    }
}
