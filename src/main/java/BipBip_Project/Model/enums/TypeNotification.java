package BipBip_Project.Model.enums;

public enum TypeNotification {
    INFORMATION("INFO"),
    WARNING("WARN"),
    ERROR("ERROR"),
    SUCCESS("SUCCESS");

    private final String value;

    TypeNotification(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
