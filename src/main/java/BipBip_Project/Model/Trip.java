package BipBip_Project.Model;





import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


import java.util.Date;

import BipBip_Project.Model.enums.ReservationType;

@Entity
@Table(name = "trip")

public class Trip {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trip_ID")
    private Long trip_ID;
    //--------------------------------------
    @Column(name = "user_ID")
    private Long user;

    
    @Column(name = "car_ID")
    private Long carDetails;
    //------------------------------------------


    @Column(name = "DeparturePoint")
    private String DeparturePoint;

    @Column(name = "ArrivalPoint")
    private String ArrivalPoint;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DepartureDateTime")
    private Date DepartureDateTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ArrivalDateTime")
    private Date ArrivalDateTime;

    @Column(name = "Description")
    private String Description;

    @Column(name = "Status")
    private String  Status;

    @Column(name = "Price")
    private Double Price;

    @Column(name = "NumberOfAvailableSeats")
    private int NumberOfAvailableSeats;

    @Column(name = "reservationType")
    private ReservationType reservationType;
    

    // // Constructeurs
    // public    Trip() {
    // }
    //-------------------------------------------------------
    public Trip(){
        
    };

    //constructeur pour la page recherche

    public Trip(String DeparturePoint, String ArrivalPoint, int NumberOfAvailableSeats) {
        
        this.DeparturePoint = DeparturePoint;
        this.ArrivalPoint = ArrivalPoint;
        this.NumberOfAvailableSeats = NumberOfAvailableSeats;
    }

    // constructeur de tous les attribus
    public Trip(Long user, Long carDetails, String DeparturePoint, String ArrivalPoint,
                  Date DepartureDateTime, Date ArrivalDateTime, String Description, String Status,
                  double Price, int NumberOfAvailableSeats) {
        this.user =user;
        this.carDetails = carDetails;
        this.DeparturePoint = DeparturePoint;
        this.ArrivalPoint = ArrivalPoint;
        this.DepartureDateTime = DepartureDateTime;
        this.ArrivalDateTime =ArrivalDateTime;
        this.Description =Description;
        this.Status =Status;
        this.Price =Price;
        this.NumberOfAvailableSeats =NumberOfAvailableSeats;
    }
    //--------------------------------------------------------------------------
    // Getters et setters
    public Long gettrip_ID() {
        return trip_ID;
    }

    public void settrip_ID(Long trip_ID) {
        this.trip_ID = trip_ID;
    }
//--------------------------------------------------
    public Long getuser() {
        return user;
    }

    public void setuser(Long user) {
        this.user = user;
    }

    public Long getcarDetails() {
        return carDetails;
    }

    public void setcarDetails(Long carDetails) {
        this.carDetails = carDetails;
    }
//-----------------------------------------------------

    public String getDeparturePoint() {
        return DeparturePoint;
    }

    public void setDeparturePoint(String DeparturePoint) {
        this.DeparturePoint = DeparturePoint;
    }

    public String getArrivalPoint() {
        return ArrivalPoint;
    }

    public void setArrivalPoint(String ArrivalPoint) {
        this.ArrivalPoint = ArrivalPoint;
    }

    public Date getDepartureDateTime() {
        return DepartureDateTime;
    }

    public void setDepartureDateTime(Date DepartureDateTime) {
        this.DepartureDateTime = DepartureDateTime;
    }

    public Date getArrivalDateTime() {
        return ArrivalDateTime;
    }

    public void setArrivalDateTime(Date ArrivalDateTime) {
        this.ArrivalDateTime = ArrivalDateTime;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getStatus() {
        return  Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public Double getPrice() {
        return  Price;
    }

    public void setPrice(Double Price) {
        this.Price = Price;
    }

    public int getNumberOfAvailableSeats() {
        return  NumberOfAvailableSeats;
    }

    public void setNumberOfAvailableSeats(int NumberOfAvailableSeats) {
        this.NumberOfAvailableSeats = NumberOfAvailableSeats;
    }

    public ReservationType getReservationType() {
        return reservationType;
    }

    public void setReservationType(ReservationType reservationType) {
        this.reservationType = reservationType;
    }
    

}
