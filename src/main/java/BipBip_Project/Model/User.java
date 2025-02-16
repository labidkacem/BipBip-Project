package BipBip_Project.Model;


import java.util.Date;

import jakarta.persistence.*;

import lombok.NoArgsConstructor;



@Entity
@Table(name = "User")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_ID;

    private String firstName;

    public Long getuser_ID() {
        return user_ID;
    }

    public String getfirstName() {
        return firstName;
    }

    public String getlastName() {
        return lastName;
    }

    public String getphoneNumber() {
        return phoneNumber;
    }

    public UserStatus getstatus() {
        return status;
    }

    private String lastName;


    @Column(unique = true)
    private String email;

    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date dateInscription;

    
    public Date getdateInscription() {
        return dateInscription;
    }

    public void setdateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }
    @PrePersist
    protected void onCreate() {
            dateInscription = new Date();
    }

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private UserStatus status = UserStatus.ACTIF;

   

    public enum UserStatus {
        ACTIF,
        INACTIF,
        SUSPENDU
    }

    public Long getIdUser() {
        return user_ID;
    }

    public void setIdUser(Long user_ID) {
        this.user_ID = user_ID;
    }

    public  String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getPhoneNumber() {
        return  phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

}