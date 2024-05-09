package com.example.ilianachakarovaemployees.util;

import lombok.NonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static final String DATE_PATTERN1 = "yyyy-MM-dd";
    public static final String DATE_REGEX1 = "([0-9]{4})-([0-9]{2})-([0-9]{2})";
    public static final String DATE_PATTERN2 = "dd/MM/yyyy";
    public static final String DATE_REGEX2 = "([0-9]{2})/([0-9]{2})/([0-9]{4})";
    public static final String DATE_PATTERN3 = "dd-MM-yyyy";
    public static final String DATE_REGEX3 = "([0-9]{2})-([0-9]{2})-([0-9]{4})";
    public static final String DATE_PATTERN4 = "dd/MM/yy";
    public static final String DATE_REGEX4 = "([0-9]{2})/([0-9]{2})/([0-9]{2})";
    public static final String DATE_PATTERN5 = "yyyyMMdd";
    public static final String DATE_REGEX5 = "([0-9]{4})([0-9]{2})([0-9]{2})";
    public static final String DATE_PATTERN6 = "yyyy/MM/dd";
    public static final String DATE_REGEX6 = "([0-9]{4})/([0-9]{2})/([0-9]{2})";

    public static LocalDate parseDate(@NonNull String date) {
        DateTimeFormatter dateTimeFormatter;
        if (date.matches(DATE_REGEX1)) {
            dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN1);
        } else if (date.matches(DATE_REGEX2)) {
            dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN2);
        } else if (date.matches(DATE_REGEX3)) {
            dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN3);
        } else if (date.matches(DATE_REGEX4)) {
            dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN4);
        }else if (date.matches(DATE_REGEX5)) {
            dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN5);
        }else if (date.matches(DATE_REGEX6)) {
            dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN6);
        } else {
            dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN1);//default
        }
        return LocalDate.parse(date, dateTimeFormatter);
    }
}
