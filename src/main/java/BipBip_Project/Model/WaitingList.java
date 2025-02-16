package BipBip_Project.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.util.Date;

import BipBip_Project.Model.Reservation.ServiceType;

import jakarta.persistence.Column;


@Entity
@Table(name = "WaitingList")
public class WaitingList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "waitingList_ID")
    private Long waitingListId;

    @Column(name = "user_ID")
    private Long userId;

    @Column(name = "service_ID")
    private Long serviceId;

    @Column(name = "service_type")
    private ServiceType serviceType;
    
    @Column(name = "date_added") 
    private Date dateAdded; 

    public WaitingList() {
    }

    public WaitingList(Long userId, Long serviceId, ServiceType serviceType, Date dateAdded) {
        this.userId = userId;
        this.serviceId = serviceId;
        this.serviceType = serviceType;
        this.dateAdded = dateAdded;
    }

    public Long getWaitingListId() {
        return waitingListId;
    }

    public void setWaitingListId(Long waitingListId) {
        this.waitingListId = waitingListId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

}
