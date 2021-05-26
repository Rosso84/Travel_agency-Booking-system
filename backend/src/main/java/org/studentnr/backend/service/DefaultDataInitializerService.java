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
        String password = "dddddd";

        attempt(() -> userService.createUser( userId,"Charles", "holmes", "Sobraj",
                "OclahomaStreet 22nd", "2020", password));


        LocalDate dateOfToday = LocalDate.now();
        LocalDate depDate= dateOfToday.plusYears(1).plusMonths(1);
        LocalDate returnDate= depDate.plusWeeks(3);
        Long franceTrip = attempt(() -> tripService.createTrip("Trip to Paris", "Perfect for family and very safe with many activities",
                30000, "france", depDate, returnDate));

        LocalDate depDate2 = depDate.plusMonths(1);
        LocalDate returnDate2 = depDate2.plusWeeks(3);
        Long egyptTrip = attempt(() -> tripService.createTrip("Trip to Giza", "Take a journey and to most ancient Perfect for couple and very exciting",
                1200, "egypt", depDate2, returnDate2));

        LocalDate depDate3 = depDate2.plusMonths(1);
        LocalDate returnDate3 = depDate3.plusWeeks(3);
        Long baliTrip = attempt(() -> tripService.createTrip("Trip to Bali", "Perfect for singles and a lot of alcohol involved",
                6000, "indonesia", depDate3, returnDate3));

        LocalDate depDate4 = depDate3.plusMonths(1);
        LocalDate returnDate4 = depDate4.plusWeeks(3);
        Long israelTrip = attempt(() -> tripService.createTrip("Trip to Israel", "Perfect for family and very safe with many activities",
                30000, "israel", depDate4, returnDate4));

        LocalDate depDate5 = depDate4.plusMonths(1);
        LocalDate returnDate5 = depDate5.plusWeeks(3);
        Long vietnamTrip = attempt(() -> tripService.createTrip("Trip for Saigon", "Perfect for couple and very exciting",
                10200, "vietnam", depDate5, returnDate5));

        LocalDate depDate6 = depDate5.plusMonths(1);
        LocalDate returnDate6 = depDate6.plusWeeks(3);
        Long mallorcaTrip = attempt(() -> tripService.createTrip("Trip to Mallorca", "Exciting, but risky. Not just alcohol involved",
                20000, "Spain", depDate6, returnDate6));

        LocalDate depDate7 = depDate6.plusMonths(1);
        LocalDate returnDate7 = depDate7.plusWeeks(3);
        Long ugandaTrip = attempt(() -> tripService.createTrip("Trip to Kampala", "Exciting journey to city. Many locations for swimming and tourguides",
                70000, "Uganda", depDate7, returnDate7));

        LocalDate depDate8 = depDate7.plusMonths(1);
        LocalDate returnDate8 = depDate8.plusWeeks(3);
        Long jamaicaTrip = attempt(() -> tripService.createTrip("Trip to Montego Bay", "Tropical place with Beautifull jungles and attractions",
                90000, "jamaica", depDate8, returnDate8));

        Long maroccoTrip = attempt(() -> tripService.createTrip("Trip to Casablanca", "Hot place with good food and close to city",
                43333, "marocco", depDate6, returnDate6));
        Long maroccoTrip2 = attempt(() -> tripService.createTrip("Trip to Marrakesh", "Hot place with good food and close to city",
                10000, "marocco", depDate6, returnDate6));
        Long maroccoTrip3 = attempt(() -> tripService.createTrip("Trip to Tangier", "Hot place with good food and close to city",
                7000, "marocco", depDate6, returnDate6));
        Long maroccoTrip4 = attempt(() -> tripService.createTrip("Trip to Agadier", "Hot place with good food and close to city",
                1233300, "marocco", depDate6, returnDate6));



        Long ethiopiaTrip = attempt(() -> tripService.createTrip("Trip to Dire Daba", "Nice people, close to banks, stores near by, many places to rent cars",
                70300, "ethiopia", depDate8, returnDate8));
        Long ethiopiaTrip2 = attempt(() -> tripService.createTrip(" Trip to Awassa", "Nice people, Perfect for couple and very exciting",
                44400, "ethiopia", depDate4, returnDate4));
        Long ethiopiaTrip3 = attempt(() -> tripService.createTrip("Luxurious trip to Gondar", "Nice people, close to banks, stores near by, many places to rent cars",
                34440, "ethiopia", depDate2, returnDate2));
        Long ethiopiaTrip4 = attempt(() -> tripService.createTrip("Backpack trip to Adama", "Nice people, close to banks, stores near by, many places to rent cars and sleep overs",
                9000, "ethiopia", depDate3, returnDate3));


        Long uruguayTrip = attempt(() -> tripService.createTrip("Trip to Salto", "Nice people, close to banks, stores near by, many places to rent cars.",
                13000, "uruguay", depDate2, returnDate2));
        Long uruguayTrip1 = attempt(() -> tripService.createTrip("Trip to Melo", "Nice people, close to banks, stores near by, many places to rent cars.",
                2000, "uruguay", depDate3, returnDate3));
         Long uruguayTrip3 = attempt(() -> tripService.createTrip("Trip to Mercedes", "Nice people, close to banks, stores near by, many places to rent cars.",
                4000, "uruguay", depDate4, returnDate4));
        Long uruguayTrip4 = attempt(() -> tripService.createTrip("Trip to Durazno", "Nice people, close to banks, stores near by, many places to rent cars.",
                8000, "uruguay", depDate8, returnDate8));


        attempt( () -> purchaseService.bookTrip( userId, franceTrip ) );
        attempt( () -> purchaseService.bookTrip( userId, egyptTrip ) );
        attempt( () -> purchaseService.bookTrip( userId, baliTrip ) );


    }



    private  <T> T attempt(Supplier<T> lambda){
        try{
            return lambda.get();
        }catch (Exception e){
            return null;
        }
    }
}
