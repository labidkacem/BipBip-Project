package BipBip_Project.Controller;


import BipBip_Project.Model.Car;
import BipBip_Project.Model.CarDetails;
import BipBip_Project.Model.Reservation;
import BipBip_Project.Model.Trip;
import BipBip_Project.Model.User;
import BipBip_Project.Model.enums.ReservationType;
import BipBip_Project.Repository.CarDetailsRepository;
import BipBip_Project.Repository.TripRepo;
import BipBip_Project.Repository.UserRepository;
import BipBip_Project.Service.ReservationService;
import BipBip_Project.Service.CarDetailsService;
import BipBip_Project.Service.CarService;
import BipBip_Project.Service.TripService;

import org.springframework.validation.BindingResult;

import lombok.Data;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model; // Assurez-vous d'importer la classe Model à partir de l'emplacement correct
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;


@Controller
@ComponentScan
// @RequestMapping("/")
public class TripController {

    @Autowired
    private TripService tripService;
    @Autowired
    private CarService CarService;
    @Autowired
    private CarDetailsService CarDetailsService;
    @Autowired
    private TripRepo tripRepo;
    @Autowired 
    private UserRepository userRepo;
    @Autowired
    private CarDetailsRepository carDetailsRepo;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReservationService reservationService;
    

    
    @GetMapping("/chooseTripType")
    public String showChooseTripTypePage(Model model) {
        return "choose_trip_type"; 
    }

    @GetMapping("/addTrip")

    public String showAddTripForm(Model model) {
        model.addAttribute("trip", new Trip());
        Long userID ;
        List<Car> cars = new ArrayList<Car>();
        List<CarDetails> allCarsDetails = new ArrayList<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            User user = userRepository.findByEmail(username);
            if(user != null ){
                userID = user.getuser_ID();
                if((CarService.getCarsByUserId(userID)).size() != 0){
                    cars = CarService.getCarsByUserId(userID);
                    for(Car car : cars){
                        allCarsDetails.add(CarDetailsService.getCarDetailsByCarId(car).get());
                    }
                    model.addAttribute("cars", allCarsDetails);
                }
                else{
                    return "redirect:/add-car-form";
                }
            }
        }
        return "add_trip"; // Nom de la page HTML pour le formulaire
    }

    

    @PostMapping("/addTrip")
    public String addTrip(@RequestParam("carId") String carName, @RequestParam("departureDate") String departureDate, @RequestParam("arrivalDate") String arrivalDate, Trip trip, Model model) throws ParseException {
        Long userID ;
        List<Car> cars = new ArrayList<Car>();
        Date departDate = new SimpleDateFormat("yyyy-MM-dd").parse(departureDate);
        Date arriveDate = new SimpleDateFormat("yyyy-MM-dd").parse(arrivalDate);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            User user = userRepository.findByEmail(username);
            if(user != null ){
                userID = user.getuser_ID();
                trip.setuser(userID);
                cars = CarService.getCarsByUserId(userID);
            }
        }   
        for(Car car : cars){
            if(CarDetailsService.getCarDetailsByCarId(car).get().getCarName().equals(carName)){
                trip.setcarDetails(car.getCarID());
                break;
            }
        }
        trip.setDepartureDateTime(departDate);
        trip.setArrivalDateTime(arriveDate);
        trip.setStatus("en attente");   
        tripRepo.save(trip); 
        return "confirmation";    
    }

    @GetMapping("/myTrips")
    public String redirectMyTrips() {
        Long userID ;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            User user = userRepository.findByEmail(username);
            if(user != null ){
                userID = user.getuser_ID();
                return "redirect:/myTrips/" + userID ;
            }
        }
        return "redirect:/login";
    }

    @GetMapping("/myTrips/{userID}")
    public String showMyTrips(@PathVariable Long userID, Model model) {
        if(userRepository.findById(userID).isPresent()){
            Long userId ;
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                String username = userDetails.getUsername();
                User user = userRepository.findByEmail(username);
                if(user != null ){
                    userId = user.getuser_ID();
                    if(userId == userID){
                        List<Trip> trips = tripService.getUserTrips(userID);
                        model.addAttribute("trips", trips);
                        return "myTrips";
                    }
                    else{
                        return "redirect:/myTrips/" + userId ;
                    }
                }
                
            }
        }
        return "redirect:/login";
    }




    // Endpoint pour mettre à jour un voyage
    @PutMapping("/update")
    public Trip updateTrip(@RequestBody Trip trip) {
        return tripService.updateTrip(trip);
    }

    @GetMapping("/trip/{id}")
    public String showTripDetails(@PathVariable Long id, Model model) {
        Optional<Trip> tripOptional = tripRepo.findById(id);
    
        if (tripOptional.isPresent()) {
            Trip trip = tripOptional.get();
            model.addAttribute("trip", trip);
            model.addAttribute("reservation", new Reservation());
            return "normal_trip_details";
        } else {
            // Gérer le cas où le voyage n'est pas trouvé
            return "redirect:/error";  // Redirection vers une page d'erreur par exemple
        }
    }

    @GetMapping("/tripdaily/{id}")
    public String showTripDetailsDaily(@PathVariable Long id, Model model) {
        Optional<Trip> tripOptional = tripRepo.findById(id);
    
        if (tripOptional.isPresent()) {
            Trip trip = tripOptional.get();
            model.addAttribute("trip", trip);
            model.addAttribute("reservation", new Reservation());
            return "daily_trips_details";
        } else {
            // Gérer le cas où le voyage n'est pas trouvé
            return "redirect:/error";  // Redirection vers une page d'erreur par exemple
        }
    }
    @PostMapping("/trip/{id}/sauver")
    public String reserveTrip(@PathVariable Trip id, @ModelAttribute Reservation reservation) {
        // Ajoutez le code pour sauvegarder la réservation dans la base de données
        // Utilisez le service ou le repository approprié pour effectuer l'opération de sauvegarde
        reservation.setTripId(id); // Assurez-vous de définir l'ID du trajet dans la réservation
        reservationService.saveReservation(reservation); // Utilisez le service approprié
        return "redirect:/trip/{id}";
    }

    @GetMapping("/trips")
    public String getAllTrips(Model model) {
       
        return "tripList";
    }

    @GetMapping("/normalTrips")
    public String getNormallTrips(Model model){
        List<Trip> trips = tripService.getAllTrips();
        model.addAttribute("trips", trips);
        return "normal_trips";
    }

     @GetMapping("/dailyTrips")
    public String getDailyTrips(Model model){
        List<Trip> trips = tripService.getAllTrips();
        model.addAttribute("trips", trips);
        return "daily_trips";
    }


    // add random trips for testings *******************************************

    private static final int MICAR_ID = 1;
    private static final int MAX_CAR_ID = 3000;
    private static final int MIUSER_ID = 1;
    private static final int MAX_USER_ID = 3000;

    private static final List<String> FRENCH_CITIES = Arrays.asList(
            "Paris", "Marseille", "Lyon", "Toulouse", "Nice", "Nantes", "Strasbourg", "Montpellier", "Bordeaux"
    );

    private static final List<String> DESCRIPTIONS = Arrays.asList(
        "Discover the beauty of the French Riviera",
        "Explore the historic streets of Paris",
        "Culinary adventure in Lyon, the gastronomic capital",
        "Scenic drive through the vineyards of Bordeaux",
        "Relaxing weekend getaway in Nice",
        "Historical tour of Strasbourg's old town",
        "Sunset cruise on the Seine River in Paris",
        "Visit the iconic Eiffel Tower",
        "Wine tasting in the Loire Valley",
        "Adventure in the French Alps"
);
    

    

    @GetMapping("/addRandomTrips")
    public String generateRandomTrip() {
        int numberOfTrips = 1000; // Spécifie le nombre d'utilisateurs que tu veux générer

        for (int i = 0; i < numberOfTrips; i++) {
            Trip trip = new Trip();
            Random random = new Random();
            // Generate random values for the fields
            trip.setcarDetails((long) (random.nextInt(MAX_CAR_ID - MICAR_ID + 1) + MICAR_ID));
            trip.setArrivalDateTime(generateRandomDateTime("20/12/2023", "30/12/2023"));
            trip.setArrivalPoint(getRandomElement(FRENCH_CITIES));
            trip.setDepartureDateTime(generateRandomDateTime("20/12/2023", "30/12/2023"));
            trip.setDeparturePoint(getRandomElement(FRENCH_CITIES));
            trip.setDescription(getRandomElement(DESCRIPTIONS));
            trip.setNumberOfAvailableSeats(random.nextInt(10) + 1); // At least one available seat
            trip.setPrice((double) (random.nextInt(91) + 10)); // Price between 0 and 1000
            trip.setStatus("ACTIF"); // Default to active status
            trip.setReservationType(new ReservationType[]{ReservationType.INSTANT, ReservationType.WAITING}[random.nextInt(2)]); 
            trip.setuser((long) (random.nextInt(MAX_USER_ID - MIUSER_ID + 1) + MIUSER_ID));
            tripRepo.save(trip);
            System.out.println(trip);
        }

        return "ajout";
    }

    private static Date generateRandomDateTime(String startDate, String endDate) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date start = dateFormat.parse(startDate);
            Date end = dateFormat.parse(endDate);
            long randomTime = ThreadLocalRandom.current().nextLong(start.getTime(), end.getTime());
            return new Date(randomTime);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getRandomElement(List<String> list) {
        Random random = new Random();
        return list.get(random.nextInt(list.size()));
    }
}













