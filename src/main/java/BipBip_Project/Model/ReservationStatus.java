package BipBip_Project.Model;


public enum ReservationStatus {
    CONFIRMED("CONFIRMED"),
    NOTCONFIRMED("NOT CONFIRMED");

    private final String value;

    ReservationStatus(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
    
}
