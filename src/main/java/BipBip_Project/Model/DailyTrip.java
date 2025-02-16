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
@Table(name = "dailyTrip")
public class DailyTrip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dailyTrip_ID")
    private Long dailyTrip_ID;

    @Column(name = "user_ID")
    private Long user;

    @Column(name = "car_ID")
    private Long carDetails;

    @Column(name = "DeparturePoint")
    private String DeparturePoint;

    @Column(name = "ArrivalPoint")
    private String ArrivalPoint;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "VacationStart")
    private Date VacationStart;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "VacationEnd")
    private Date VacationEnd;


    @Column(name = "Status")
    private String Status;

    @Column(name = "Price")
    private Double Price;

    @Column(name = "NumberOfAvailableSeats")
    private int NumberOfAvailableSeats;

    @Column(name = "reservationType")
    private ReservationType reservationType;


    public DailyTrip(){

    };


    public DailyTrip(Long dailyTrip_ID, Long user, Long carDetails, String DeparturePoint, String ArrivalPoint, Date VacationStart, Date VacationEnd,String Status, Double Price, int NumberOfAvailableSeats) {
        this.dailyTrip_ID = dailyTrip_ID;
        this.user = user;
        this.carDetails = carDetails;
        this.DeparturePoint = DeparturePoint;
        this.ArrivalPoint = ArrivalPoint;
        this.VacationStart = VacationStart;
        this.VacationEnd = VacationEnd;
        this.Status = Status;
        this.Price = Price;
        this.NumberOfAvailableSeats = NumberOfAvailableSeats;
    }
    

    public Long getdailyTrip_ID() {
        return dailyTrip_ID;
    }

    public void setdailyTrip_ID(Long dailyTrip_ID) {
        this.dailyTrip_ID = dailyTrip_ID;
    }

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

    public Date getVacationStart() {
        return VacationStart;
    }

    public void setVacationStart(Date VacationStart) {
        this.VacationStart = VacationStart;
    }

    public Date getVacationEnd() {
        return VacationEnd;
    }

    public void setVacationEnd(Date VacationEnd) {
        this.VacationEnd = VacationEnd;
    }


    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }


    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double Price) {
        this.Price = Price;
    }

    public int getNumberOfAvailableSeats() {
        return NumberOfAvailableSeats;
    }

    public void setN_NumberOfAvailableSeats(int NumberOfAvailableSeats) {
        this.NumberOfAvailableSeats = NumberOfAvailableSeats;
    }

    public ReservationType getReservationType() {
        return reservationType;
    }

    public void setReservationType(ReservationType reservationType) {
        this.reservationType = reservationType;
    }
}
