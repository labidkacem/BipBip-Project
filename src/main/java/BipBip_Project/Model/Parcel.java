package BipBip_Project.Model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
@Data
@Table(name = "parcel")
public class Parcel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parcel_id")
    private Long parcelId;

    @Column(name = "user_id")
    private Long userID;

    @Column(name = "trip_id")
    private Long tripId;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_trip")
    private TypeTrip typeTrip;

    @Enumerated(EnumType.STRING)
    @Column(name = "size")
    private Size size;

    @Column(name = "width")
    private double width;

    @Column(name = "height")
    private double height;

    @Column(name = "length")
    private double length;

    @Transient
    private MultipartFile[] photos;

    private List<String> imagePaths;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut")
    private Statut statut;

    @Column(name = "weight")
    private double weight;


    // Enums
    public enum TypeTrip {
        NORMAL, DAILY
    }

    public enum Size {
        S, M, L, XL, XXL
    }

    public enum Statut {
        SEARCHING, PENDING, SHIPPED, DELIVERED
    }

    
    public Parcel() {
    }

    public Parcel(Long userID, Long tripId, TypeTrip typeTrip, Size size, double width,
                   double height, double length, MultipartFile[] photos, String title,
                   String description, Statut statut, double weight) {
        this.userID = userID;
        this.tripId = tripId;
        this.typeTrip = typeTrip;
        this.size = size;
        this.width = width;
        this.height = height;
        this.length = length;
        this.photos = photos;
        this.title = title;
        this.description = description;
        this.statut = statut;
        this.weight = weight;
    }

    // Getters and setters
    
    public Long getParcelId() {
        return parcelId;
    }

    public void setParcelId(Long parcelId) {
        this.parcelId = parcelId;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }

    public TypeTrip getTypeTrip() {
        return typeTrip;
    }

    public void setTypeTrip(TypeTrip typeTrip) {
        this.typeTrip = typeTrip;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public MultipartFile[] getPhotos() {
        return photos;
    }

    public void setPhotos(MultipartFile[] photos) {
        this.photos = photos;
    }

    public List<String> getImagePaths() {
        return imagePaths;
    }

    public void setImagePaths(List<String> imagePaths) {
        this.imagePaths = imagePaths;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
