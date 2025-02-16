package BipBip_Project.Model;
import BipBip_Project.Model.enums.DayEnum;
import BipBip_Project.Model.enums.TypeEnum;


import jakarta.persistence.*;
import java.util.Date;
import java.util.List;



@Entity
@Table(name = "dailyTripDay")
public class DailyTripDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dailyTripDay_ID")
    private Long dailyTripDay_ID;

    @Column(name = "dailyTrip_ID")
    private Long dailyTrip_ID;

    
    @Enumerated(EnumType.STRING)
    @Column(name = "DayEnum")
    DayEnum DayEnums;



    @Enumerated(EnumType.STRING)
    @Column(name = "TypeEnum")
    private TypeEnum TypeEnum;

    @Column(name = "NumberOfAvailableSeats")
    private int AvailableSeats;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DepartureDateTime")
    private Date DepartureDateTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ReturnDateTime")
    private Date ReturnDateTime;

    public DailyTripDay() {
    }

    public DailyTripDay(Long dailyTrip_ID, DayEnum  DayEnums, TypeEnum TypeEnum, int AvailableSeats, Date DepartureDateTime, Date ReturnDateTime) {
        this.dailyTrip_ID = dailyTrip_ID;
        this.DayEnums  = DayEnums ;
        this.TypeEnum = TypeEnum;
        this.AvailableSeats = AvailableSeats;
        this.DepartureDateTime = DepartureDateTime;
        this.ReturnDateTime = ReturnDateTime;
    }

    public Long getdailyTripDay_ID() {
        return dailyTripDay_ID;
    }

    public void setdailyTripDay_ID(Long dailyTripDay_ID) {
        this.dailyTripDay_ID = dailyTripDay_ID;
    }

    public Long getdailyTrip_ID() {
        return dailyTrip_ID;
    }

    public void setdailyTrip_ID(Long dailyTrip_ID) {
        this.dailyTrip_ID = dailyTrip_ID;
    }


    public DayEnum getN_DayEnums() {
        return DayEnums;
    }

    public void setDayEnums(DayEnum DayEnums) {
        this.DayEnums = DayEnums;
    }




    public TypeEnum getTypeEnum() {
        return TypeEnum;
    }

    public void setTypeEnum(TypeEnum TypeEnum) {
        this.TypeEnum = TypeEnum;
    }

    public int getAvailableSeats() {
        return AvailableSeats;
    }

    public void setAvailableSeats(int AvailableSeats) {
        this.AvailableSeats = AvailableSeats;
    }

    public Date getDepartureDateTime() {
        return DepartureDateTime;
    }

    public void setDepartureDateTime(Date DepartureDateTime) {
        this.DepartureDateTime = DepartureDateTime;
    }

    public Date getReturnDateTime() {
        return ReturnDateTime;
    }

    public void setReturnDateTime(Date ReturnDateTime) {
        this.ReturnDateTime = ReturnDateTime;
    }

}
