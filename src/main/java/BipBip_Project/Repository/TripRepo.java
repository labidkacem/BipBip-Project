package BipBip_Project.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import BipBip_Project.Model.Trip;

import java.util.Date;
import java.util.List;


@Repository
public interface TripRepo extends JpaRepository<Trip, Long> {

    public Trip findById(long n_trip_ID);    
    public List<Trip> findAll();    

}
    


