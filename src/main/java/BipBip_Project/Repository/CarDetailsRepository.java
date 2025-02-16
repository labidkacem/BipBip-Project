package BipBip_Project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import BipBip_Project.Model.Car;
import BipBip_Project.Model.CarDetails;
import BipBip_Project.Model.CarImages;

@Repository
public interface CarDetailsRepository extends JpaRepository<CarDetails, Long>{
    

}

    