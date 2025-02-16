package BipBip_Project.Service;

import BipBip_Project.Model.Trip;
import BipBip_Project.Repository.TripRepo;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Service
public class TripService {
    // Injectez le repository Trip ici
    private final TripRepo nTripRepository;

    // @Autowired
    public TripService(TripRepo nTripRepository) {
        this.nTripRepository = nTripRepository;
    }



    // Méthode pour ajouter un voyage
    public Trip addTrip(Trip trip) {
        // Ajoutez la logique pour enregistrer le voyage dans la base de données
        // Utilisez le repository Trip pour cela
        // Retournez le voyage ajouté
        return nTripRepository.save(trip);
    }

    // Méthode pour mettre à jour un vo yage
    public Trip updateTrip(Trip trip) {
        // Ajoutez la logique pour mettre à jour le voyage dans la base de données
        // Utilisez le repository Trip pour cela
        // Retournez le voyage mis à jour
        return nTripRepository.save(trip); 
    }

    public List<Trip> getAllTrips() {
        return nTripRepository.findAll();
    }



    public List<Trip> searchTrips(String departureCity, String destinationCity, int numberOfPersons , Date travelDate) {
        List<Trip> allTrips = getAllTrips();
        Calendar travelCalendar = Calendar.getInstance();
        travelCalendar.setTime(travelDate);
        List<Trip> matchingTrips = new ArrayList<>();
        for (Trip trip : allTrips) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(trip.getDepartureDateTime());
            if(trip.getDeparturePoint().equals(departureCity) && trip.getArrivalPoint().equals(destinationCity) && trip.getNumberOfAvailableSeats() >= numberOfPersons  && calendar.get(Calendar.YEAR) == travelCalendar.get(Calendar.YEAR) && calendar.get(Calendar.MONTH) == travelCalendar.get(Calendar.MONTH) && calendar.get(Calendar.DAY_OF_MONTH) == travelCalendar.get(Calendar.DAY_OF_MONTH)) {
                matchingTrips.add(trip);
            }
        }
        return matchingTrips;
    }

     public List<Trip> getUserTrips(Long userID) {
        List<Trip> allTrips = getAllTrips();
        List<Trip> matchingTrips = new ArrayList<>();
        for (Trip trip : allTrips) {
            if(trip.getuser() == userID) {
                matchingTrips.add(trip);
            }
        }
        return matchingTrips;
    }

    
    
    // Autres méthodes pour gérer les voyages (par exemple, supprimer, obtenir par ID, etc.)
}
