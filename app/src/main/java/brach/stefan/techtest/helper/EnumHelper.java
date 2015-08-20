package brach.stefan.techtest.helper;

public class EnumHelper {
    public static <T extends Enum<T>> T valueOfIgnoreCase(Class<T> enumeration, String name, T fallback) {
        for (T enumValue : enumeration.getEnumConstants()) {
            if (enumValue.name().equalsIgnoreCase(name)) {
                return enumValue;
            }
        }
        return fallback;
    }
}

