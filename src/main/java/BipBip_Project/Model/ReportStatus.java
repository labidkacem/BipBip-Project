package BipBip_Project.Model;

import java.util.Arrays;
import java.util.Optional;

public enum ReportStatus {
    PENDING("Pending"),
    REVIEWED("Reviewed"),
    RESOLVED("Resolved");

    private final String value;

    ReportStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    // Récupérer un ReportStatus depuis une valeur String
    public static Optional<ReportStatus> fromValue(String value) {
        return Arrays.stream(ReportStatus.values())
                     .filter(status -> status.getValue().equalsIgnoreCase(value))
                     .findFirst();
    }

    // Vérifier si une valeur est un statut valide
    public static boolean isValidStatus(String value) {
        return fromValue(value).isPresent();
    }
}
/*
package BipBip_Project.Model;

public enum ReportStatus {
    PENDING("Pending"),
    REVIEWED("Reviewed"),
    RESOLVED("Resolved");

    private final String value;

    ReportStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    
    
}
 */