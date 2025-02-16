package BipBip_Project.Service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BipBip_Project.Model.Car;
import BipBip_Project.Model.CarDetails;
import BipBip_Project.Model.Parcel;
import BipBip_Project.Model.Trip;

import java.util.ArrayList;
import java.util.Date;

import BipBip_Project.Repository.CarDetailsRepository;
import BipBip_Project.Repository.ParcelRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ParcelService {

    private final ParcelRepository ParcelRepository;
    private final TripService TripService;
    private final CarDetailsRepository CarDetailsRepository;

    @Autowired
    public ParcelService(ParcelRepository ParcelRepository, TripService TripService, CarDetailsRepository CarDetailsRepository) {
        this.ParcelRepository = ParcelRepository;
        this.TripService =TripService;
        this.CarDetailsRepository = CarDetailsRepository;
    }

    public List<Parcel> getAllParcels() {
        return ParcelRepository.findAll();
    }

    public Parcel getParcelById(Long parcelId) {
        return ParcelRepository.findById(parcelId).orElse(null);
    }

    public Parcel saveParcel(Parcel parcel) {
        return ParcelRepository.save(parcel);
    }

    public void deleteParcel(Long parcelId) {
        ParcelRepository.deleteById(parcelId);
    }

    public List<Trip> getSearchResults(String departureCity, String destinationCity, Date travelDate, Parcel Parcel) {
        List<Trip> allTrips = TripService.searchTrips(departureCity, destinationCity, 0, travelDate);
        CarDetails car ;
        List<Trip> matchingTrips =  new ArrayList<>(allTrips.size());
        for (Trip trip : allTrips) {
            car = CarDetailsRepository.findById(trip.getcarDetails()).get();
            if(car.getCarHeight()>Parcel.getHeight() && car.getCarWidth()>Parcel.getWidth() && car.getCarLength()>Parcel.getLength()) {
                matchingTrips.add(trip);
            }
            }
        
        return matchingTrips;
    }

}
