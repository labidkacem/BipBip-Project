package BipBip_Project.Controller;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import BipBip_Project.Model.Car;
import BipBip_Project.Model.CarDetails;
import BipBip_Project.Model.CarImages;
import BipBip_Project.Model.PhotoForm;
import BipBip_Project.Repository.CarDetailsRepository;
import BipBip_Project.Repository.CarImagesRepository;
import BipBip_Project.Repository.CarRepository;

@Controller
@RequestMapping("/")
public class CarController {
    private final CarRepository CarRepository;
    private final CarDetailsRepository CarDetailsRepository;
    private final CarImagesRepository CarImagesRepository;

    private long CarDetailsID;
    private long CarID;
    List<String> imagePaths = new ArrayList<>();
    private static String uploadDirectory = "src/main/resources/static/";

    @Autowired
    public CarController(CarRepository CarRepository, CarDetailsRepository CarDetailsRepository, CarImagesRepository CarImagesRepository) {
        this.CarRepository = CarRepository;
        this.CarDetailsRepository=CarDetailsRepository;
        this.CarImagesRepository=CarImagesRepository;
    }

    @GetMapping("/add-car-form")
    public String showCarForm(CarDetails CarDetails, Car Car, PhotoForm PhotoForm) {
        return "addCar"; 
    }


    @PostMapping("/addCarDetails")
    public String addCarDetails(CarDetails CarDetails, BindingResult result, Model model){
        CarDetailsRepository.save(CarDetails);
        CarDetailsID = CarDetails.getCarDetailID();
        return "redirect:/add-car-form";
    }

    @PostMapping("/uploadPhotos")
    public String uploadPhotos(PhotoForm PhotoForm, BindingResult result, Model model) {

        for (MultipartFile file : PhotoForm.getPhotos()) {
            if (file != null && !file.isEmpty()) {
                try {
                    // Générez un nom de fichier unique
                    String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                    // Enregistrez le fichier dans le répertoire de ressources
                    Files.write(Paths.get(uploadDirectory, fileName), file.getBytes());
                    // Ajoutez le chemin du fichier à la liste
                    String imagePath = "/static/" + fileName; 
                    imagePaths.add(imagePath);

                    // Enregistrez le chemin de l'image dans l'entité CarImages
                  //CarImages CarImage = new CarImages(CarID, imagePath);
                 // CarImagesRepository.save(CarImage);

                } catch (IOException e) {
                  //e.printStacTrace();
                }
            }
        }
        // Ajoutez la liste des chemins d'images au modèle pour une utilisation ultérieure
        model.addAttribute("imagePaths", imagePaths);
        return "ajout";
    }

    @PostMapping("/addCar")
    public String addCar(Car Car, CarDetails CarDetails, BindingResult result, Model model){
        Car.setCarDetails(CarDetailsID);
        Car.setUserID((long) 1);
        Car.setImagesPath(imagePaths);
        CarRepository.save(Car);
        CarID = Car.getCarID();
        return "redirect:/add-car-form";
    }

    /*@PutMapping("/update/{carId}")
    public ResponseEntity<Car> updateCar(@PathVariable Long carId, @RequestBody Car updatedCar) {
        Car updated = CarRepository.updateCar(carId, updatedCar);
        if (updated != null) {
            return ResponseEntity.o(updated);
        } else {
            return ResponseEntity.notFound().build(); 
        }
    }*/

    /*@DeleteMapping("/cancel/{carId}")
    public ResponseEntity<Void> cancelCar(@PathVariable Long carId) {
        CarRepository.cancelCar(carId);
        return ResponseEntity.o().build();
    }*/











    // generer des voitures details pour tester **********************************************************************************************************************


    @GetMapping("/generateCarsDetails")
    private String generateRandomCarDetails() {

         for (int i = 0; i < 1000; i++) {
            
        
            CarDetails carDetails = new CarDetails();
            Random random = new Random();

            // Generate random values for the fields
            carDetails.setAirConditioning(random.nextBoolean());
            carDetails.setCarColor(generateRandomColor());
            carDetails.setCarHeight(random.nextInt(201) + 50); // Generates int values between 50 and 250
            carDetails.setCarLength(random.nextInt(201) + 50); // Generates int values between 50 and 250
            carDetails.setCarModel(getRandomElement(CAR_MODELS));
            carDetails.setCarName(getRandomElement(CAR_NAMES));
            carDetails.setCarType(getRandomElement(CAR_TYPES));
            carDetails.setCarWidth(random.nextInt(201) + 50); // Generates int values between 50 and 250
            CarDetailsRepository.save(carDetails);
         }
        return "ajout";
    }



    private static final List<String> CAR_MODELS = Arrays.asList("Toyota", "Honda", "Ford", "BMW", "Mercedes");
    private static final List<String> CAR_NAMES = Arrays.asList("Civic", "Corolla", "Accord", "Camry", "Mustang");
    private static final List<String> CAR_TYPES = Arrays.asList("Scooter", "Car");

  
    

    private static String generateRandomColor() {
        List<String> colors = Arrays.asList("Red", "Blue", "Green", "White", "Blac");
        return getRandomElement(colors);
    }

    private static String getRandomElement(List<String> list) {
        Random random = new Random();
        return list.get(random.nextInt(list.size()));
    }


    /// generer voiture avec images pour tester **********************************************************************************************************************

    private static final List<String> IMAGE_PATHS = Arrays.asList(
            "resources/imagesCars/bmw.jpeg", "resources/imagesCars/ford.jpeg", "resources/imagesCars/honda.jpg", "resources/imagesCars/mercedeces.jpeg", "resources/imagesCars/toyota.jpeg"
    );

    @GetMapping("/generateCars")
    private String generateRandomCars() {
        for (int i = 0; i < 1500; i++) {
            Car car = new Car();
            Random random = new Random();
            car.setCarDetails((long) random.nextInt(1000) + 1); // Generates int values between 1 and 1000
            car.setImagesPath(generateRandomImagesPaths());
            car.setNumImmC(generateRandomImmatriculation());
            car.setUserID((long)random.nextInt(3000) + 1); // Generates int values between 1 and 3000
            CarRepository.save(car);
        }
        return "ajout";
    }
    

    

    private static List<String> generateRandomImagesPaths() {
        List<String> randomImagesPaths = new ArrayList<>();
        Random random = new Random();

        
        for (int i = 0; i < 1; i++) {
            randomImagesPaths.add(getRandomElement(IMAGE_PATHS));
        }

        return randomImagesPaths;
    }

    private static String generateRandomImmatriculation() {
        Random random = new Random();
        char letter1 = (char) ('A' + random.nextInt(26));
        char letter2 = (char) ('A' + random.nextInt(26));
        char letter3 = (char) ('A' + random.nextInt(26));
        int digit1 = random.nextInt(10);
        char letter4 = (char) ('A' + random.nextInt(26));
        int digit2 = random.nextInt(10);
        int digit3 = random.nextInt(10);
        char letter5 = (char) ('A' + random.nextInt(26));
        int digit4 = random.nextInt(10);
        char letter6 = (char) ('A' + random.nextInt(26));

        return String.format("%c%c_%c%d%c%d%d_%c%d%c", letter1, letter2, letter3, digit1, letter4,
                digit2, digit3, letter5, digit4, letter6);
    }

   


}

