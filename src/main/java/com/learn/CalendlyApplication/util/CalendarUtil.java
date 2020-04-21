package com.learn.CalendlyApplication.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
public class CalendarUtil {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
    public static String fromLocalDateToString(LocalDate localDate) {
        return localDate.format(DATE_FORMATTER);
    }
    public static String fromLocalTimeToString(LocalTime localTime) {
        LocalTime updatedLocalTime = localTime.truncatedTo(ChronoUnit.HOURS);
        return updatedLocalTime.format(TIME_FORMATTER);
    }
    public static LocalDate fromStringToLocalDate(String localDate) {
        return LocalDate.parse(localDate, DATE_FORMATTER);
    }
    public static LocalTime fromStringToLocalTime(String localTime) {
        return LocalTime.parse(localTime,TIME_FORMATTER);
    }
}
