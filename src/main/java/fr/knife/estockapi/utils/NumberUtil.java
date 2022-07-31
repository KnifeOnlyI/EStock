package fr.knife.estockapi.utils;

import java.util.Locale;

/**
 * Utilitary class for numbers
 */
public final class NumberUtil {
    /**
     * Hidden constructor
     */
    private NumberUtil() {
    }

    /**
     * Format the specified number to a french format accounting
     *
     * @param value The value to format
     *
     * @return The formatted value
     */
    public static String formatToAccounting(Double value) {
        return value == null ? "" : String.format("%s €", String.format(Locale.FRANCE, "%.3f", value));
    }

    /**
     * Format the specified number to a points format
     *
     * @param value The value to format
     *
     * @return The formatted value
     */
    public static String formatToPoints(Double value) {
        return value == null ? "" : String.format("%s", String.format(Locale.FRANCE, "%.0f", value));
    }
}
