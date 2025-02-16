package BipBip_Project.Model.enums;


public enum ReservationType {
    INSTANT("INSTANT"),
    WAITING("WAITING");
    

    private final String value;

    ReservationType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
