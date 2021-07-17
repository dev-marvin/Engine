package me.tuskdev.engine.util;

public class NumberUtil {

    public static Integer tryParseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException exception) {
            return null;
        }
    }

}
