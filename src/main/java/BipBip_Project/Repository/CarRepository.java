package BipBip_Project.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import BipBip_Project.Model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>{

    
} 