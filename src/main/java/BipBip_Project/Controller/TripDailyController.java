package BipBip_Project.Controller;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import BipBip_Project.Model.Car;
import BipBip_Project.Model.CarDetails;
import BipBip_Project.Model.DailyTrip;
import BipBip_Project.Model.DailyTripDay;
import BipBip_Project.Model.User;
import BipBip_Project.Model.enums.DayEnum;
import BipBip_Project.Model.enums.TypeEnum;
import BipBip_Project.Repository.CarDetailsRepository;
import BipBip_Project.Repository.DailyTripDayRepo;
import BipBip_Project.Repository.DailyTripRepo;
import BipBip_Project.Repository.UserRepository;
import BipBip_Project.Service.CarDetailsService;
import BipBip_Project.Service.CarService;
import BipBip_Project.Service.DailyTripService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Date;


@Controller
@ComponentScan
public class TripDailyController {

@Autowired
private DailyTripService DailyTripService;
@Autowired
private CarService CarService;
@Autowired
private CarDetailsService CarDetailsService;

@Autowired
private DailyTripRepo DailyTripRepo;

@Autowired 
private DailyTripDayRepo DailyTripDayRepo;
@Autowired 
private UserRepository userRepository;



//----------------------------------
private long dailyTripID;


//-------------------------------------


    public TripDailyController(DailyTripRepo DailyTripRepo,DailyTripDayRepo DailyTripDayRepo) {
        this.DailyTripRepo = DailyTripRepo;
        this.DailyTripDayRepo = DailyTripDayRepo;
    }


    @GetMapping("/addDailyTrip")

    public String showAddDailyTripForm(Model model) {
        model.addAttribute("dailyTrip", new DailyTrip());
        model.addAttribute("dailyTripDay", new DailyTripDay());

        Long userID ;
        List<Car> cars = new ArrayList<Car>();
        List<CarDetails> allCarsDetails = new ArrayList<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            User user = userRepository.findByEmail(username);
            if(user != null ){
                userID = user.getIdUser();
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
        return "add_dailyTrip"; 
    }



        @PostMapping("/addDailyTrip")
        public String addDailyTrip(@RequestParam("carId") String carName,
        @RequestParam("vacationStart") String vacationStart,
        @RequestParam("vacationEnd") String vacationEnd,
        DailyTrip dailyTrip, Model model) throws ParseException {
        Long userID;
        List<Car> cars = new ArrayList<>();
        Date start = new SimpleDateFormat("yyyy-MM-dd").parse(vacationStart);
        
        Date end =  new SimpleDateFormat("yyyy-MM-dd").parse(vacationEnd);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserDetails) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        User user = userRepository.findByEmail(username);

        if (user != null) {
        userID = user.getIdUser();
        dailyTrip.setuser(userID);
        cars = CarService.getCarsByUserId(userID);
        }
        }

        for (Car car : cars) {
        if (CarDetailsService.getCarDetailsByCarId(car).get().getCarName().equals(carName)) {
    //  dailyTrip.set_carDetails(car.getCarID());
        break;
        }
        }

        dailyTrip.setVacationStart(start);
        dailyTrip.setVacationEnd(end);
        dailyTrip.setStatus("en attente");
        
        DailyTripRepo.save(dailyTrip);
        
        dailyTripID = dailyTrip.getdailyTrip_ID();
        return "redirect:/addDailyTrip";
        }


        @PostMapping("addDailyTripDay")
        public String addDailyTripDay(DailyTrip dailyTrip,DailyTripDay dailyTripDay,BindingResult result,Model model){
            dailyTripDay.setdailyTrip_ID(dailyTripID);
            
            
            
            DailyTripDayRepo.save(dailyTripDay);
            return "redirect:/addDailyTrip";
        }

        private static final List<String> villes = Arrays.asList("Saint etienne", "Lyon", "Paris", "Toulouse", "Bordeaux", "Nice", "Rennes", "Strasbourg");


    private static DayEnum getRandomDay() {
        Random random = new Random();
        DayEnum[] days = DayEnum.values();
        return days[random.nextInt(days.length)];
    }
  
    private static TypeEnum getRandomType() {
        Random random = new Random();
        TypeEnum[] types = TypeEnum.values();
        return types[random.nextInt(types.length)];
    }

    private static int getRandomNumber() {
        return ThreadLocalRandom.current().nextInt(1, 5);
    }

    private static double getRandomPrice() {
        return ThreadLocalRandom.current().nextInt(3, 30);
    }

    private String getRandomElement(List<String> list) {
        Random random = new Random();
        return list.get(random.nextInt(list.size()));
    }


        @GetMapping("/addDTrips")
        public String addDailyTrips() {
            int numberOfTrips = 1000; // Spécifie le nombre d'utilisateurs que tu veux générer
            for (int i = 0; i < numberOfTrips; i++) { 
                DailyTrip trip = new DailyTrip();
                Random random = new Random();

                long carId = (long)(random.nextInt(600)+1);


                //generationg tripday
                long id = (long) (random.nextInt(600)+1);
                trip.setuser(id);
                System.out.println("CAR ID " + carId);
                trip.setcarDetails(carId);
                if(trip.getcarDetails().equals(null)){
                    trip.setcarDetails(1L
                );
                }
                trip.setDeparturePoint(getRandomElement(villes));
                trip.setArrivalPoint(getRandomElement(villes));
                trip.setStatus("A venir");
                trip.setPrice(getRandomPrice());
             // trip.setNumberOfAvailableSeats(getRandomNumber());
                  
   
            
                DailyTripRepo.save(trip);
            }
            return "ajout";
}



}