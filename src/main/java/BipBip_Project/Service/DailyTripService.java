package BipBip_Project.Service;



import BipBip_Project.Model.DailyTrip;
import BipBip_Project.Repository.DailyTripRepo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class DailyTripService {

    private final DailyTripRepo nDailyTripRepository;

    public DailyTripService(DailyTripRepo nDailyTripRepository) {
        this.nDailyTripRepository = nDailyTripRepository;
    }

    public DailyTrip addDailyTrip(DailyTrip dailyTrip) {
        return nDailyTripRepository.save(dailyTrip);
    }

    public DailyTrip updateDailyTrip(DailyTrip dailyTrip) {
        return nDailyTripRepository.save(dailyTrip);
    }

    public List<DailyTrip> getAllDailyTrips() {
        return nDailyTripRepository.findAll();
    }

    public List<DailyTrip> searchDailyTrips(String departureCity, String destinationCity, int numberOfPersons, Date travelDate) {
        List<DailyTrip> allDailyTrips = getAllDailyTrips();
        Calendar travelCalendar = Calendar.getInstance();
        travelCalendar.setTime(travelDate);
        List<DailyTrip> matchingTrips = new ArrayList<>();
        for (DailyTrip dailyTrip : allDailyTrips) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dailyTrip.getVacationStart());
            if (dailyTrip.getDeparturePoint().equals(departureCity)
                    && dailyTrip.getArrivalPoint().equals(destinationCity)
                    && dailyTrip.getNumberOfAvailableSeats() >= numberOfPersons
                    && calendar.get(Calendar.YEAR) == travelCalendar.get(Calendar.YEAR)
                    && calendar.get(Calendar.MONTH) == travelCalendar.get(Calendar.MONTH)
                    && calendar.get(Calendar.DAY_OF_MONTH) == travelCalendar.get(Calendar.DAY_OF_MONTH)) {
                matchingTrips.add(dailyTrip);
            }
        }
        return matchingTrips;
    }

    public List<DailyTrip> getUserDailyTrips(Long userID) {
        List<DailyTrip> allDailyTrips = getAllDailyTrips();
        List<DailyTrip> matchingTrips = new ArrayList<>();
        for (DailyTrip dailyTrip : allDailyTrips) {
            if (dailyTrip.getuser() == userID) {
                matchingTrips.add(dailyTrip);
            }
        }
        return matchingTrips;
    }



    public List<DailyTrip> searchDailyTrips(String departureCity, String destinationCity) {
        List<DailyTrip> allDailyTrips = getAllDailyTrips();
        System.out.println("entree dans searchdailytrips!!!!");
        List<DailyTrip> matchingTrips = new ArrayList<>();
        System.out.println(matchingTrips);
        for (DailyTrip dailyTrip : allDailyTrips) {
           
            //System.out.println("dans search daily on va chercher un depart de " + departureCity + " et une arrivee en " + destinationCity);
            if (dailyTrip.getDeparturePoint().equals(departureCity)
                    && dailyTrip.getArrivalPoint().equals(destinationCity)
                ) {
                
                matchingTrips.add(dailyTrip);
                System.out.println("Service daily: " + matchingTrips.get(0).getDeparturePoint());
            }
            
        }
        return matchingTrips;
    }

    

    
}
