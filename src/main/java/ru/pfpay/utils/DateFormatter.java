package ru.pfpay.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.pfpay.config.Format;

import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;

public class DateFormatter {

    public final static String PATTERN_ISO_DATE = "yyyy-MM-dd";
    public final static String PATTERN_SQL_DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    private static final Logger LOGGER = LoggerFactory.getLogger(DateFormatter.class);

    public static String format(LocalDate value) {
        return format(value, PATTERN_ISO_DATE);
    }

    public static LocalDate parseLocalDate(String value) {
        return parseLocalDate(value, PATTERN_ISO_DATE);
    }

    public static String format(TemporalAccessor value, String pattern) {
        if (value == null) {
            return Format.EMPTY_STRING;
        }
        return format(value, DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDate parseLocalDate(String value, String pattern) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        return parseLocalDate(value, DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDateTime parseLocalDateTime(String value, String pattern) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        return parseLocalDateTime(value, DateTimeFormatter.ofPattern(pattern));
    }

    private static String format(TemporalAccessor value, DateTimeFormatter formatter) {
        try {

            return formatter.format(value);

        } catch (DateTimeException exception) {

            LOGGER.error(exception.getMessage(), exception);
        }

        return null;
    }

    private static LocalDate parseLocalDate(String value, DateTimeFormatter formatter) {
        try {

            return LocalDate.parse(value, formatter);

        } catch (DateTimeParseException exception) {

            LOGGER.error(exception.getMessage(), exception);
        }

        return null;
    }

    private static LocalDateTime parseLocalDateTime(String value, DateTimeFormatter formatter) {
        try {

            return LocalDateTime.parse(value, formatter);

        } catch (DateTimeParseException exception) {

            LOGGER.error(exception.getMessage(), exception);
        }

        return null;
    }
}
