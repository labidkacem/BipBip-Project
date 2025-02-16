package BipBip_Project.Service;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BipBip_Project.Model.Car;
import BipBip_Project.Model.CarDetails;
import BipBip_Project.Repository.CarDetailsRepository;


@Service
public class CarDetailsService {
    private final CarDetailsRepository carDetailsRepository;

    @Autowired
    public CarDetailsService(CarDetailsRepository carDetailsRepository) {
        this.carDetailsRepository = carDetailsRepository;
    }
    private List<CarDetails> carDetailsList;
    // cherchez si il y a deja des details identiques 
    public boolean carDetailsExist(String CarName, String CarModel, String CarColor, String CarType, double     CarHeight, double     CarLength, double     CarWidth, boolean     AirConditioning) {
        carDetailsList = carDetailsRepository.findAll();
        return (carDetailsList.stream().anyMatch(existingCarDetails ->
            existingCarDetails.getCarName().equals(CarName) &&
            existingCarDetails.getCarModel().equals(CarModel) &&
            existingCarDetails.getCarColor().equals(CarColor) &&
            existingCarDetails.getCarType().equals(CarType) &&
            existingCarDetails.getCarHeight() == CarHeight &&
            existingCarDetails.getCarLength() == CarLength &&
            existingCarDetails.getCarWidth() == CarWidth &&
            existingCarDetails.isAirConditioning() == AirConditioning
        ));
    }

    public Long saveCarDetails(String CarName, String CarModel, String CarColor, String CarType, double CarHeight, double CarLength, double CarWidth, boolean AirConditioning) 
    {
        carDetailsList = carDetailsRepository.findAll();
        boolean existingCarDetails = carDetailsExist(CarName, CarModel, CarColor, CarType, CarHeight, CarLength, CarWidth, AirConditioning);
        Long a=(long) 0;
        if (!existingCarDetails) {
                CarDetails carDetails = new CarDetails(CarName, CarModel, CarColor, CarType,CarHeight, CarLength, CarWidth, AirConditioning);
            carDetailsRepository.save(carDetails);
            return carDetails.getCarDetailID();
        }
        return a;
             
    }



    public Optional<CarDetails> getCarDetailsByCarId(Car car) {
        return carDetailsRepository.findById(car.getCarDetails());
    }

    
}

