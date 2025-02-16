package BipBip_Project.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BipBip_Project.Repository.ReservationRepo;
import BipBip_Project.Model.Reservation;

@Service
public class ReservationService {

    private final ReservationRepo ReservationRepo;

    @Autowired
    public ReservationService(ReservationRepo ReservationRepository) {
        this.ReservationRepo = ReservationRepository;
    }

    public void saveReservation(Reservation reservation) {

        ReservationRepo.save(reservation);
    }

    

}
