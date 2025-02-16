package BipBip_Project.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import BipBip_Project.Model.Reservation;
import BipBip_Project.Model.User;

@Repository

public interface ReservationRepo  extends JpaRepository<Reservation,Long>{
    public Reservation findById(long n_reservation_ID);    
    public List<Reservation> findByReservee(User reservee);
}