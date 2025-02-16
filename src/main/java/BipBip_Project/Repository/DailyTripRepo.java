package BipBip_Project.Repository;




import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import BipBip_Project.Model.DailyTrip;

@Repository
public interface DailyTripRepo extends JpaRepository<DailyTrip, Long> {

    //public List<n_DailyTrip> findAll();   
} 