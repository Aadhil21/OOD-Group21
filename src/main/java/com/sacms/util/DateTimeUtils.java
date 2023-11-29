package com.sacms.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {
    public static String toISODate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
        return date.format(formatter);
    }
    public static String getDateTime(){
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDateTime = currentDateTime.format(formatter);
        return formattedDateTime;
    }

    public static boolean isDateTimeOverlapping(LocalDateTime start1, LocalDateTime end1,
                                                LocalDateTime start2, LocalDateTime end2) {
        return (start1.isAfter(start2) && start1.isBefore(end2)) || // event1 starts during event2
                (end1.isAfter(start2) && end1.isBefore(end2)) || // event1 ends during event2
                (start1.isBefore(start2) && end1.isAfter(end2)) || // event2 happens during event1
                (start1.isEqual(start2) && end1.isEqual(end2)); // event1 and event2 are starts on same time
    }
}
