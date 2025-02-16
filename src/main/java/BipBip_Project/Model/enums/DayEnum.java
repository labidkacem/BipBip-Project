package BipBip_Project.Model.enums;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum DayEnum {
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday"),
    SUNDAY("Sunday");

    private final String value;

    DayEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    // Récupérer tous les jours sous forme de liste
    public static List<DayEnum> getAllDays() {
        return Arrays.asList(values());
    }

    // Récupérer un enum depuis une valeur String
    public static Optional<DayEnum> fromValue(String value) {
        return Arrays.stream(DayEnum.values())
                     .filter(day -> day.getValue().equalsIgnoreCase(value))
                     .findFirst();
    }

    // Vérifier si une valeur est un jour valide
    public static boolean isValidDay(String value) {
        return fromValue(value).isPresent();
    }
}
/*
package BipBip_Project.Model.enums;


import java.util.Arrays;
import java.util.List;

public enum DayEnum {
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday"),
    SUNDAY("Sunday");

    private final String value;

    DayEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

        public static List<DayEnum> getAllDays() {
            return Arrays.asList(values());
        }    
}
 */