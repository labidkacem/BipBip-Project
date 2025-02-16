package BipBip_Project.Model;

import BipBip_Project.Model.enums.TypeNotification;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long notificationId;

    @Column(name = "message")
    private String message;

    @Column(name = "user_id")
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_notification")
    private TypeNotification typeNotification;

    public Notification() {
    }

    public Notification(String message, Long userId, TypeNotification typeNotification) {
        this.message = message;
        this.userId = userId;
        this.typeNotification = typeNotification;
    }

    public Long getNotificationId() {
        return this.notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public String getMessage() {
        return this.message;
    }

    public Long getUserId() {
        return this.userId;
    }

    public TypeNotification getTypeNotification() {
        return this.typeNotification;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setTypeNotification(TypeNotification typeNotification) {
        this.typeNotification = typeNotification;
    }

    

}