package BipBip_Project.Model;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "CarDetails")
public class CarDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CarDetailID")
    private Long CarDetailID;

    @Column(name = "CarName")
    private String CarName;

    @Column(name = "CarModel")
    private String CarModel;

    @Column(name = "CarColor")
    private String CarColor;

    @Column(name = "CarType")
    private String CarType;

    @Column(name = "CarHeight")
    private double CarHeight;

    @Column(name = "CarLength")
    private double CarLength;

    @Column(name = "CarWidth")
    private double CarWidth;

    @Column(name = "AirConditioning")
    private boolean AirConditioning;

    // Constructeurs
    public CarDetails() {
    }

    public CarDetails(String CarName, String CarModel, String CarColor, String CarType,
                      double CarHeight, double CarLength, double CarWidth, boolean AirConditioning) {
        this.CarName = CarName;
        this.CarModel = CarModel;
        this.CarColor = CarColor;
        this.CarType = CarType;
        this.CarHeight = CarHeight;
        this.CarLength = CarLength;
        this.CarWidth = CarWidth;
        this.AirConditioning = AirConditioning;
    }

    // Getters et setters
    public Long getCarDetailID() {
        return CarDetailID;
    }

    public void setCarDetailID(Long CarDetailID) {
        this.CarDetailID = CarDetailID;
    }

    public String getCarName() {
        return CarName;
    }

    public void setCarName(String CarName) {
        this.CarName = CarName;
    }

    public String getCarModel() {
        return CarModel;
    }

    public void setCarModel(String CarModel) {
        this.CarModel = CarModel;
    }

    public String getCarColor() {
        return CarColor;
    }

    public void setCarColor(String CarColor) {
        this.CarColor = CarColor;
    }

    public String getCarType() {
        return CarType;
    }

    public void setCarType(String CarType) {
        this.CarType = CarType;
    }

    public double getCarHeight() {
        return CarHeight;
    }

    public void setCarHeight(double CarHeight) {
        this.CarHeight = CarHeight;
    }

    public double getCarLength() {
        return CarLength;
    }

    public void setCarLength(double CarLength) {
        this.CarLength = CarLength;
    }

    public double getCarWidth() {
        return CarWidth;
    }

    public void setCarWidth(double CarWidth) {
        this.CarWidth = CarWidth;
    }

    public boolean isAirConditioning() {
        return AirConditioning;
    }

    public void setAirConditioning(boolean AirConditioning) {
        this.AirConditioning = AirConditioning;
    }
}

