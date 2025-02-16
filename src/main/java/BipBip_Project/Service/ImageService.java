/*
package BipBip_Project.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BipBip_Project.Model.CarImages;
import BipBip_Project.Repository.CarImagesRepository;

@Service
public class ImageService {
    private final CarImagesRepository carImagesRepository;

    @Autowired
    public ImageService(CarImagesRepository carImagesRepository) {
        this.carImagesRepository = carImagesRepository;
    }

    public void saveImages(List<String> imagePaths, Long CarId) {
        if (imagePaths != null && !imagePaths.isEmpty()) {
            for (String imagePath : imagePaths) {
                CarImages carImage = new CarImages(CarId, imagePath);
                
                // Enregistrez l'image dans la base de données
                carImagesRepository.save(carImage);
                
            }
        }
    }

    public List<String> getImagesPathsByCarId(Long carId) {
        Optional<CarImages> carImages = carImagesRepository.findById(carId);
        List<String> imagePaths = carImages.stream().map(CarImages::getImagePath).collect(Collectors.toList());
        return imagePaths;
    }


}

package BipBip_Project.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BipBip_Project.Model.CarImages;
import BipBip_Project.Repository.CarImagesRepository;

@Service
public class ImageService {
    private final CarImagesRepository carImagesRepository;

    @Autowired
    public ImageService(CarImagesRepository carImagesRepository) {
        this.carImagesRepository = carImagesRepository;
    }

    public void saveImages(List<String> imagePaths, Long carId) {
        if (imagePaths != null && !imagePaths.isEmpty()) {
            for (String imagePath : imagePaths) {
                CarImages carImage = new CarImages(carId, imagePath);
                carImagesRepository.save(carImage);
            }
        }
    }

    public List<String> getImagesPathsByCarId(Long carId) {
        List<CarImages> carImagesList = carImagesRepository.findByCarId(carId);
        return carImagesList.stream().map(CarImages::getImagePath).collect(Collectors.toList());
    }
}

*/
package BipBip_Project.Service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import BipBip_Project.Model.Car;
import BipBip_Project.Model.CarImages;
import BipBip_Project.Repository.CarImagesRepository;

@Service
public class ImageService {
    private final CarImagesRepository carImagesRepository;

    @Autowired
    public ImageService(CarImagesRepository carImagesRepository) {
        this.carImagesRepository = carImagesRepository;
    }

    public void saveImages(List<String> imagePaths, Car car) {
        if (imagePaths != null && !imagePaths.isEmpty()) {
            for (String imagePath : imagePaths) {
                CarImages carImage = new CarImages(car, imagePath);
                carImagesRepository.save(carImage);
            }
        }
    }

    public List<String> getImagesPathsByCarId(Long carId) {
        List<CarImages> carImagesList = carImagesRepository.findByCar_CarID(carId);
        return carImagesList.stream().map(CarImages::getImagePath).collect(Collectors.toList());
    }
}

