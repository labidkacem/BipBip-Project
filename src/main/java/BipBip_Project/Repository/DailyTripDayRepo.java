package BipBip_Project.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import BipBip_Project.Model.DailyTripDay;

@Repository
public interface DailyTripDayRepo  extends JpaRepository<DailyTripDay, Long >{
    
}

