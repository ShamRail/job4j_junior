package ru.job4j.design.srp.utils;

import java.util.Calendar;

public class DateFormatter {
    public static String format(Calendar calendar) {
        if (calendar == null) {
            return "";
        }
        return String.format(
                "%d.%d.%d %d:%d",
                calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.HOUR),
                calendar.get(Calendar.MINUTE)
        );
    }
}
