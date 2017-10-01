package de.naju.ahlen.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class Util {

    /**
     * DateFormat for all dates in the application.
     * @return SimpleDateFormat
     */
    public static SimpleDateFormat dateFormat() {
        return new SimpleDateFormat("dd.MM.yyyy");
    }

    /**
     * DecimalFormat for all decimal numbers in the application.
     * @return DecimalFormat
     */
    public static DecimalFormat decimalFormat() {
        return new DecimalFormat("0.00 €");
    }
}
