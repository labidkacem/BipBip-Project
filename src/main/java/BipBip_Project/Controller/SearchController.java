package BipBip_Project.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import BipBip_Project.Model.DailyTrip;
import BipBip_Project.Model.Trip;
import BipBip_Project.Repository.TripRepo;
import BipBip_Project.Service.DailyTripService;
import BipBip_Project.Service.TripService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class SearchController {

    private final TripService TripService;
    private final TripRepo TripRepo;
    private final DailyTripService DailyTripService;
    public SearchController(TripService TripService, TripRepo TripRepo, DailyTripService DailyTripService) {
        this.TripService = TripService;
        this.TripRepo = TripRepo;
        this.DailyTripService = DailyTripService;
    }

    @GetMapping("/search")
    public String searchTrips(@ModelAttribute Trip Trip, Model model){
        // des trips pour tester search
        /*Trip Trip1 = new Trip("a", "a", 3);
        Trip1.setDepartureDateTime(new Date());
        Trip1.setZ_user((long) 1);
        Trip Trip2 = new Trip("a", "a", 2);
        Trip2.setDepartureDateTime(new Date());
        Trip2.setZ_user((long) 1);
        Trip Trip3 = new Trip("a", "a", 2);
        Trip3.setDepartureDateTime(new Date());
        Trip3.setZ_user((long) 2);
        TripRepo.save(Trip1);
        TripRepo.save(Trip2);
        TripRepo.save(Trip3);*/
        return "search";
    }

    @PostMapping("/search")
    public String searchTripsPost(@ModelAttribute Trip Trip, @RequestParam("date") String dateString, Model model) throws ParseException {

        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        Trip.setDepartureDateTime(date);

        // Utiliser ces paramètres pour interagir avec la base de données et rechercher les voyages correspondants
        List<Trip> matchingTrips = TripService.searchTrips(Trip.getDeparturePoint(), Trip.getArrivalPoint(), Trip.getNumberOfAvailableSeats() , Trip.getDepartureDateTime());

        // Ajouter les résultats de la recherche au modèle pour les afficher dans la vue
        model.addAttribute("matchingTrips", matchingTrips);

        return "search";
    }

    @GetMapping("/searchDailyTrip")
    public String searchDailyTrips(@ModelAttribute Trip Trip, Model model){
        return "search";
    }

    @PostMapping("/searchDailyTrips")
    public String searchDailyTripsPost(@ModelAttribute Trip Trip, @RequestParam("date") String dateString, Model model){
        // Utiliser ces paramètres pour interagir avec la base de données et rechercher les voyages correspondants
        List<DailyTrip> matchingTrips = DailyTripService.searchDailyTrips(Trip.getDeparturePoint(), Trip.getArrivalPoint());

        // Ajouter les résultats de la recherche au modèle pour les afficher dans la vue
        model.addAttribute("matchingTrips", matchingTrips);

        return "search";


    }



}
