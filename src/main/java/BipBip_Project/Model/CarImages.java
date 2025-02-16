/*
package BipBip_Project.Model;

import java.sql.Blob;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "CarImages")
public class CarImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CarImageID")
    private Long CarImageID;

    @Column(name = "CarID")
    private Long Car;

    @Column(name = "ImagePath")
    private String ImagePath;

    public CarImages() {
    }

    public CarImages(Long Car, String ImagePath) {
        this.Car = Car;
        this.ImagePath = ImagePath;
    }

    public Long getCarImageID() {
        return CarImageID;
    }

    public void setCarImageID(Long CarImageID) {
        this.CarImageID = CarImageID;
    }

    public Long getCar() {
        return Car;
    }

    public void setCar(Long Car) {
        this.Car = Car;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String ImagePath) {
        this.ImagePath = ImagePath;
    }
}
 */
package BipBip_Project.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "CarImages")
public class CarImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CarImageID")
    private Long carImageID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CarID", nullable = false)
    private Car car;

    @Column(name = "ImagePath")
    private String imagePath;

    public CarImages() {
    }

    public CarImages(Car car, String imagePath) {
        this.car = car;
        this.imagePath = imagePath;
    }

    public Long getCarImageID() {
        return carImageID;
    }

    public void setCarImageID(Long carImageID) {
        this.carImageID = carImageID;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
