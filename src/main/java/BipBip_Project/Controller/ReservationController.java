package BipBip_Project.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import BipBip_Project.Model.ReservationStatus;
import BipBip_Project.Model.Reservation;
import BipBip_Project.Model.Trip;
import BipBip_Project.Model.User;
import BipBip_Project.Model.Reservation.ServiceType;
import BipBip_Project.Repository.ReservationRepo;
import BipBip_Project.Repository.TripRepo;
import BipBip_Project.Service.ReservationService;

import BipBip_Project.Repository.UserRepository;

import jakarta.servlet.http.HttpSession;
import lombok.Data;


@Controller
public class ReservationController {

    @Autowired
    ReservationRepo reservationRepo;
    @Autowired
    ReservationService reservationService;
    @Autowired
    TripRepo tripRepo;
    @Autowired
    UserRepository userRepo;


    @GetMapping("/reservation")
    public String add_reservation(Model model){

        model.addAttribute("reservation", new Reservation());
        return "add_reservation";
    }

    @PostMapping("/reservation")
    public String submitReservation(@ModelAttribute("reservation") Reservation l_reservation ){
        l_reservation.setreservationDate(new Date());
        l_reservation.setpaymentsituation(false);
        l_reservation.setstatus(ReservationStatus.NOTCONFIRMED);
        reservationRepo.save(l_reservation);
        return "confirmation";
    }
    
    @PostMapping("trip/{id}/reserver")
    public String handleReservation(@PathVariable Long id, @ModelAttribute Reservation reservation,@ModelAttribute Trip trajet, Model model, HttpSession session){
        Long User_ID = (Long) session.getAttribute("userID");
        Optional<User> reservee;
        if (User_ID == null) {
            return "redirect:/home";
        }else{
            reservee = userRepo.findById(User_ID);
        }
        Trip trip = tripRepo.findById(id).orElse(new Trip());
        if (trip != null) {
            if((reservation.getnumberOfReservation() <= trip.getNumberOfAvailableSeats()) && reservation.getnumberOfReservation() > 0){
                reservation.setreservationDate(new Date());
                reservation.setServiceType(ServiceType.NORMAL);
                reservation.setReservee(reservee.get());
                reservation.setTripId(trip);
                reservation.setstatus(ReservationStatus.valueOf("CONFIRMED"));
                trip.setNumberOfAvailableSeats(trip.getNumberOfAvailableSeats() - reservation.getnumberOfReservation());

                reservationRepo.save(reservation);
                System.out.println("nombre de places restants apres reservation " + trip.getNumberOfAvailableSeats());
                return "confirmatioreservation";
            } 
            else{
                model.addAttribute("error", "choissez un nombre raisonnable de siège");
            }

        }
        return "redirect:/normal_trip_details";
    }

    @PostMapping("trip/{id}/reserverdaily")
    public String handleReservationDaily(@PathVariable Long id, @ModelAttribute Reservation reservation,@ModelAttribute Trip trajet, Model model, HttpSession session){
        Long User_ID = (Long) session.getAttribute("userID");
        Optional<User> reservee;
        if (User_ID == null) {
            return "redirect:/home";
        }else{
            reservee = userRepo.findById(User_ID);
        }
        Trip trip = tripRepo.findById(id).orElse(new Trip());
        if (trip != null) {
            if((reservation.getnumberOfReservation() <= trip.getNumberOfAvailableSeats()) && reservation.getnumberOfReservation() > 0){
                reservation.setreservationDate(new Date());
                reservation.setServiceType(ServiceType.DAILY);
                reservation.setReservee(reservee.get());
                reservation.setTripId(trip);
                reservation.setstatus(ReservationStatus.valueOf("CONFIRMED"));
                trip.setNumberOfAvailableSeats(trip.getNumberOfAvailableSeats() - reservation.getnumberOfReservation());
                reservationRepo.save(reservation);
                System.out.println("nombre de places restants apres reservation " + trip.getNumberOfAvailableSeats());
                return "confirmatioreservation";
            } 
            else{
                model.addAttribute("error", "choissez un nombre raisonnable de siège");
            }

        }
        return "redirect:/normal_trip_details";
    }
    //method to display reservations made by a user
    @RequestMapping("/mesreservations")
    public String displayReservations(HttpSession session, Model model){
        Long User_ID = (Long) session.getAttribute("userID");
        Optional<User> reservee;
        if (User_ID == null) {
            return "redirect:/home";
        }else{
            reservee = userRepo.findById(User_ID);
        }
        List<Reservation> reservations = reservationRepo.findByReservee(reservee.get());
        model.addAttribute("reservations", reservations);
        model.addAttribute("lastName", reservee.get().getlastName());
        return "myreservations";
    }

    @RequestMapping("/saveRating")
    public String notation(Model model, @RequestParam("rating") int rating, @RequestParam("reservatioID") Reservation idReservation) {
        System.out.println("Vous êtes dans la méthode notation du contrôleur");
        System.out.println("salut");
        Reservation reservation = reservationRepo.findById(idReservation.getReservation_ID()).orElse(new Reservation());
        reservation.setRating(rating);
        reservationRepo.save(reservation);
        System.out.println("l'id de la reservation est " + idReservation);
        // Utilisez l'ID et la notation comme nécessaire
        System.out.println("rating:  " + rating);
        //System.out.println("Notation : " + rating);
    
        // Autres opérations...
    
        return "z_home";
    }
 
}