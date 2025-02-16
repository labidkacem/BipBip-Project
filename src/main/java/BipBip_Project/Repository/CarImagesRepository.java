
/* 
package BipBip_Project.Repository; 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import BipBip_Project.Model.Car;
import BipBip_Project.Model.CarImages;

@Repository
public interface CarImagesRepository extends JpaRepository<CarImages, Long>{
    
}
*/
package BipBip_Project.Repository; 

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import BipBip_Project.Model.CarImages;

@Repository
public interface CarImagesRepository extends JpaRepository<CarImages, Long> {
    
    // Recherche toutes les images associées à une voiture donnée
    List<CarImages> findByCar_CarID(Long carId);
}
