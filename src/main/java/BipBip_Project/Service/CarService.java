package BipBip_Project.Service;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import BipBip_Project.Model.Car;
import BipBip_Project.Model.CarDetails;
import BipBip_Project.Model.CarImages;
import BipBip_Project.Repository.CarRepository;

@Service
public class CarService {
    private final CarRepository  CarRepository;
    private final ImageService ImageService; // Injectez le service d'images ici
    private final CarDetailsService CarDetailsService; // Injectez le service details de vehicule ici


    @Autowired
    public CarService(CarRepository CarRepository, ImageService ImageService, CarDetailsService CarDetailsService) {
        this.CarRepository = CarRepository;
        this.ImageService = ImageService;
        this.CarDetailsService = CarDetailsService;
    }

    public Car createCar(Long UserID, String NumImmC, List<String> ImagesPath, String CarName, String CarModel, String CarColor, String CarType, double CarHeight, double CarLength, double CarWidth, boolean AirConditioning) {

        Long CarDetails = CarDetailsService.saveCarDetails(CarName, CarModel, CarColor, CarType, CarHeight, CarLength, CarWidth, AirConditioning);
        // Créez une instance de Car
        Car car = new Car(UserID, CarDetails, NumImmC);

        //Créez des instances Carimages et les attribuer a Car

     // ImageService.saveImages(ImagesPath, car.getCarID());

        car.setImagesPath(ImageService.getImagesPathsByCarId(car.getCarID()));

        


        
    
        // Enregistrez la voiture dans la base de données (ou effectuez d'autres opérations nécessaires)
        return car;
    }

    public Car addCar(Car car){
        return CarRepository.save(car);
    }

    public List<Car> getCarsByUserId(Long userID) {
        List<Car> allCars= CarRepository.findAll();
        List<Car> userCars = new ArrayList<Car>();
        for (Car car : allCars) {
            if (car.getUserID() == userID) {
                userCars.add(car);
            }
        }
        return userCars;
    }


    public Car updateCar(Long carId, Car updatedCar) {
        Car existingCar = CarRepository.findById(carId).orElse(null);
        if (existingCar != null) {
            // Mettez à jour les propriétés de la voiture existante avec les nouvelles valeurs de updatedCar
            existingCar.setCarDetails(updatedCar.getCarDetails());
            existingCar.setImagesPath(ImageService.getImagesPathsByCarId(updatedCar.getCarID()));
            existingCar.setNumImmC(updatedCar.getNumImmC());
            return CarRepository.save(existingCar);
        } else {
            return null; 
        }
    }

    public void cancelCar(Long carId) {
        CarRepository.deleteById(carId);
    }
}

