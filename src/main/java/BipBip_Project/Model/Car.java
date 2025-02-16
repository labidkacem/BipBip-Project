package BipBip_Project.Model;


import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name = "Car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CarID")
    private Long CarID;

    @Column(name = "UserID")
    private Long UserID;

    @Column(name = "CarDetailID")
    private Long CarDetails;

    @Column(name = "NumImmC")
    private String NumImmC;
    
    private List<String> ImagesPath;

    // Constructeurs
    public Car() {
    }

    public Car(Long UserID, Long CarDetails , String NumImmC) {
        this.UserID = UserID;
        this.CarDetails = CarDetails;
        this.NumImmC = NumImmC;
    }

    // Getters et setters
    public Long getCarID() {
        return CarID;
    }

    public void setCarID(Long CarID) {
        this.CarID = CarID;
    }

    public Long getUserID() {
        return UserID;
    }

    public void setUserID(Long UserID) {
        this.UserID = UserID;
    }

    public Long getCarDetails() {
        return CarDetails;
    }

    public void setCarDetails(Long CarDetails) {
        this.CarDetails = CarDetails;
    }


    public String getNumImmC() {
        return NumImmC;
    }

    public void setNumImmC(String NumImmC) {
        this.NumImmC = NumImmC;
    }

    public List<String> getImagesPath(){
       
        return ImagesPath;
    }

    public void setImagesPath(List<String> ImagesPath) {
        this.ImagesPath = ImagesPath;
    }
}
