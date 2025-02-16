
/*
package BipBip_Project.Controller;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;

import BipBip_Project.Model.Car;
import BipBip_Project.Model.CarDetails;
import BipBip_Project.Model.DailyTrip;
import BipBip_Project.Model.Trip;
import BipBip_Project.Model.User;
import BipBip_Project.Repository.CarDetailsRepository;
import BipBip_Project.Repository.CarRepository;
import BipBip_Project.Repository.PreferencesRepository;
import BipBip_Project.Repository.UserRepository;
import BipBip_Project.Service.UserService;
import BipBip_Project.Service.DailyTripService;
import BipBip_Project.Service.TripService;

import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




//@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8081", methods = {RequestMethod.POST}, allowedHeaders = {"Content-Type"})

public class RestController {
    @Inject
    private UserService userService;
    @Inject
    private TripService n_TripService;
    @Inject
    private DailyTripService dailyTripService;
    @Inject 
    private UserRepository userRepository;
    @Inject 
    private CarRepository kCarRepository;
    @Inject 
    private CarDetailsRepository kCarDetailsRepository;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody User user) {
        try {
            userService.setComputingDerivedPassword(user, user.getPassword());
            userRepository.save(user);
            return ResponseEntity.ok().body("ok");
        } catch (Exception e) {
            // Gérer les erreurs
            return ResponseEntity.badRequest().body("bad");
        }
    }

    @PostMapping("/search")
    public ResponseEntity<String> search(@RequestBody Map<String, Object> searchRequest) {
        try {
            // Access the properties of searchRequest dynamically
            String departure = (String) searchRequest.get("departure");
            String destination = (String) searchRequest.get("destination");
            String dateString = (String) searchRequest.get("date");
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
            int seats = (int) searchRequest.get("seats");
            System.out.println("Searching for trips from " + departure + " to " + destination + " on " + date + " with " + seats + " seats");
            List<Trip> matchingTrips = n_TripService.searchTrips(departure, destination, seats, date);
            System.out.println(
                    "Found " + matchingTrips.size() + " matching trips for " + departure + " to " + destination + " on " + date + " with " + seats + " seats"
            );
            

            // Create a new Map and populate it with desired information
            Map<String, Object> jsonResponse = new HashMap<>();
            List<Map<String, Object>> tripsResponseList = new ArrayList<>();
            for(Trip trip : matchingTrips) {
                Map<String, Object> tripResponse = new HashMap<>();
                tripResponse.put("depart", trip.getDeparturePoint());
                tripResponse.put("time", trip.getDepartureDateTime());
                tripResponse.put("arrival", trip.getArrivalPoint());
                tripResponse.put("price", trip.getPrice());
                tripResponse.put("seats", trip.getNumberOfAvailableSeats());
                Long carId = trip.getcarDetails();
                Optional<Car> car = CarRepository.findById(carId);
                if (car != null) {
                    String carImagePath = car.get().getImagesPath().get(0);
                    if(carImagePath == "/resources/imagesCars/bmw.jpeg") {
                        carImagePath = "toyota.jpeg";
                    }
                    tripResponse.put("carImage", carImagePath);
                    Optional<CarDetails> carDetails = kCarDetailsRepository.findById(car.get().getKCarDetails());
                    if (carDetails != null) {
                        tripResponse.put("car", carDetails.get().getCarModel() + " " + carDetails.get().getKCarName());
                    }else{
                        tripResponse.put("car", "Honda Civic");
                    }
                }else{
                    tripResponse.put("carImage", "resources/imagesCars/toyota.jpeg");
                    tripResponse.put("car", "Honda Civic");
                }
                Long userId = trip.getuser();
                Optional<User> user = userRepository.findById(userId);
                tripResponse.put("user", user.get().getFirstName() + " " + user.get().getLastName());
                tripsResponseList.add(tripResponse);
            }

            jsonResponse.put("trips", tripsResponseList);
            // Use MappingJackson2HttpMessageConverter to convert the list to JSON
            MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
            String jsonString = converter.getObjectMapper().writeValueAsString(jsonResponse);
            
            return ResponseEntity.ok().body(jsonString);
        } catch (Exception e) {
            // Handle errors appropriately
            return ResponseEntity.badRequest().body("bad");
        }
    }



    @PostMapping("/searchDaily")
    public ResponseEntity<String> searchDaily(@RequestBody Map<String, Object> searchRequest) {
        try {
            // Access the properties of searchRequest dynamically
            String departure = (String) searchRequest.get("departure");
           // System.out.println("depart de " + departure);
            String arrival = (String) searchRequest.get("arrival");
           // System.out.println("arrivée à " + arrival);
     
            List<String> selectedDays = (List<String>) searchRequest.get("selecdays");
            Map<String, Object> tripTypes = (Map<String, Object>) searchRequest.get("triptype");
            System.out.println("Searching for trips from " + departure + " to " + arrival );
            List<DailyTrip> matchingTrips = dailyTripService.searchDailyTrips(departure, arrival);
            System.out.println(
                    "Found " + matchingTrips.size() + " matching trips for " + departure + " to " + arrival 
            );
           // System.out.println(matchingTrips.get(0).getDeparturePoint());
          //  System.out.println(matchingTrips.get(0).getArrivalPoint());
           // System.out.println(matchingTrips.get(1).getDeparturePoint());
           // System.out.println(matchingTrips.get(1).getArrivalPoint());

            Map<String, Object> jsonResponse = new HashMap<>();
            List<Map<String, Object>> tripsResponseList = new ArrayList<>();
            for(DailyTrip trip : matchingTrips) {
                Map<String, Object> tripResponse = new HashMap<>();
                tripResponse.put("depart", trip.getDeparturePoint());
                tripResponse.put("arrival", trip.getArrivalPoint());
                tripResponse.put("price", trip.getPrice());
                tripResponse.put("seats", trip.getNumberOfAvailableSeats());
                System.out.println("depart: " + trip.getDeparturePoint() + " " + trip.getArrivalPoint() + " " + trip.getPrice() + " " + trip.getNumberOfAvailableSeats());
                Long carId = trip.getcarDetails();
                System.out.println("carId: " + carId);
                Optional<Car> car = kCarRepository.findById(carId);
                System.out.println("Car" + car);
                if (car.isEmpty()) {
                    System.out.println("hna");
                    tripResponse.put("car", "Honda Civic");
                    System.out.println("dzt");
                    String carImagePath = car.get().getImagesPath().get(0);
                   tripResponse.put("carImage", carImagePath);
                   
                }else{
                    tripResponse.put("carImage", "/resources/imagesCars/toyota.jpeg");
                    System.out.println("optionnal temchi");
                    Optional<CarDetails> carDetails = CarDetailsRepository.findById(car.get().getCarDetails());
                    if (carDetails.isEmpty()) {
                        tripResponse.put("car", "Honda Civic");
                    }else{
                        tripResponse.put("car", carDetails.get().getCarModel() + " " + carDetails.get().getCarName());
                        System.out.println("car details ");
                    }
                }
                
                Long userId = trip.getuser();
                System.out.println("id "+ userId);
                Optional<User> user = userRepository.findById(userId);
                System.out.println(user);
                if (user.isPresent()) {
                    tripResponse.put("user", user.get().getFirstName() + " " + user.get().getLastName());

                }else{
                    tripResponse.put("user", "aymen ben");
                }
                tripsResponseList.add(tripResponse);
            }

            jsonResponse.put("trips", tripsResponseList);
            // Use MappingJackson2HttpMessageConverter to convert the list to JSON
            MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
            String jsonString = converter.getObjectMapper().writeValueAsString(jsonResponse);
            
            return ResponseEntity.ok().body(jsonString);
        } catch (Exception e) {
            // Handle errors appropriately
            return ResponseEntity.badRequest().body("bad");
    }
}

    

    
}

 */