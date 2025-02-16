package BipBip_Project.Model.enums;


public enum TypeEnum {
    ONEWAY("One Way Trip"),
    RETURN("Return trip"),
    ROUND("Round Trip");
    
    private final String value;

    TypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    
}
