package ru.pfpay.utils;

import ru.pfpay.config.Format;

import java.sql.Timestamp;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateUtils {

    public static final TimeZone UTC_TIME_ZONE = TimeZone.getTimeZone("UTC");
    public static final TimeZone MSK_TIME_ZONE = TimeZone.getTimeZone("UTC+3");

    public static final ZoneOffset MSK_ZONE_OFFSET = ZoneOffset.of("+3");

    public static final String[] MONTH_NAMES = {"январь", "февраль", "март", "апрель", "май", "июнь", "июль", "август", "сентябрь", "октябрь", "ноябрь", "декабрь"};


    public static LocalDateTime createLocalDateTimeFromStartOfDay(LocalDate date) {
        if (date == null) {
            return null;
        }
        return LocalDateTime.of(date, LocalTime.MIN);
    }

    public static LocalDateTime createLocalDateTimeFromEndOfDay(LocalDate date) {
        if (date == null) {
            return null;
        }
        return LocalDateTime.of(date, LocalTime.MAX);
    }


    public static Instant createInstantFromMskStartOfDay(LocalDate date) {
        if (date == null) {
            return null;
        }
        return LocalDateTime.of(date, LocalTime.MIN).toInstant(MSK_ZONE_OFFSET);
    }

    public static Instant createInstantFromMskEndOfDay(LocalDate date) {
        if (date == null) {
            return null;
        }
        return LocalDateTime.of(date, LocalTime.MAX).toInstant(MSK_ZONE_OFFSET);
    }

    public static String getMonthName(Integer monthNumber) {
        if (monthNumber == null || monthNumber < 1 || monthNumber > 12) {
            return Format.EMPTY_STRING;
        }
        return MONTH_NAMES[monthNumber - 1];
    }


    public static java.sql.Date createSqlDate(LocalDate date) {
        if (date == null) {
            return null;
        }
        return java.sql.Date.valueOf(date);
    }

    public static LocalDate getLocalDate(Object object) {
        if (object instanceof java.sql.Date) {
            return ((java.sql.Date) object).toLocalDate();
        }
        return null;
    }

    public static Instant createInstant(LocalDateTime value) {
        if (value == null) {
            return null;
        }
        return value.toInstant(MSK_ZONE_OFFSET);
    }

    public static Instant createInstant(Timestamp value) {
        if (value == null) {
            return null;
        }
        return value.toInstant();
    }


    public static LocalDate createLocalDate(Object object) {

        if (object == null) {
            return null;
        }

        if (object instanceof LocalDate) {
            return (LocalDate) object;
        }

        if (object instanceof java.sql.Date) {
            return createLocalDate((java.sql.Date) object);
        }

        if (object instanceof Instant) {
            return createLocalDate((Instant) object);
        }

        return null;
    }

    public static LocalDate createLocalDate(java.sql.Date date) {
        if (date == null) {
            return null;
        }
        return date.toLocalDate();
    }

    public static LocalDate createLocalDate(Instant instant) {
        if (instant == null) {
            return null;
        }
        return createLocalDateTime(instant).toLocalDate();
    }

    public static LocalDateTime createLocalDateTime(Instant instant) {
        if (instant == null) {
            return null;
        }
        return instant.atOffset(MSK_ZONE_OFFSET).toLocalDateTime();
    }

    public static LocalDateTime createLocalDateTime(ZonedDateTime zonedDateTime) {
        if (zonedDateTime == null) {
            return null;
        }
        return zonedDateTime.toInstant().atOffset(MSK_ZONE_OFFSET).toLocalDateTime();
    }

    public static Calendar createCalendar(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        return new GregorianCalendar(localDate.getYear(), localDate.getMonthValue() - 1, localDate.getDayOfMonth());
    }

    public static Integer calculateDayCount(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) {
            return null;
        }

        long period = ChronoUnit.DAYS.between(startDate, endDate) + 1;

        if (period > 0 && period <= Integer.MAX_VALUE) {
            return new Integer((int) period);
        }

        return null;
    }

    public static Integer calculateDayCount(Integer year, Integer month) {
        if (year == null || month == null) {
            return null;
        }
        return YearMonth.of(year, month).lengthOfMonth();
    }

    public static boolean isBetween(LocalDate currentDate, LocalDate startDate, LocalDate endDate) {
        if (currentDate != null) {
            if (startDate != null && startDate.compareTo(currentDate) <= 0) {
                if (endDate == null || endDate.compareTo(currentDate) >= 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean ge(LocalDate first, LocalDate second) {
        if (first == null || second == null) {
            return false;
        }
        return first.compareTo(second) >= 0;
    }

    public static boolean gt(LocalDate first, LocalDate second) {
        if (first == null || second == null) {
            return false;
        }
        return first.compareTo(second) > 0;
    }


    public static boolean le(LocalDate first, LocalDate second) {
        if (first == null || second == null) {
            return false;
        }
        return first.compareTo(second) <= 0;
    }

    public static boolean lt(LocalDate first, LocalDate second) {
        if (first == null || second == null) {
            return false;
        }
        return first.compareTo(second) < 0;
    }

    public static LocalDate max(LocalDate first, LocalDate second) {
        if (first == null && second == null) {
            return null;
        }
        if (first != null && second != null) {
            return first.compareTo(second) < 0 ? second : first;
        }
        if (first != null) {
            return first;
        }
        return second;
    }

    public static LocalDate[] sort(LocalDate... dates) {
        Arrays.sort(dates, (first, second) -> {
            if (first == null && second == null) {
                return 0;
            }
            if (first != null && second == null) {
                return 1;
            }
            if (first == null && second != null) {
                return -1;
            }
            return first.compareTo(second);
        });

        return dates;
    }

    public static LocalDate min(LocalDate first, LocalDate second) {
        if (first == null && second == null) {
            return null;
        }
        if (first != null && second != null) {
            return first.compareTo(second) < 0 ? first : second;
        }
        if (first != null) {
            return first;
        }
        return second;
    }

    public static LocalDate min(LocalDate... dates) {
        if (dates.length == 0) {
            return null;
        }
        return sort(dates)[0];
    }

    public static LocalDate max(LocalDate... dates) {
        if (dates.length == 0) {
            return null;
        }
        return sort(dates)[dates.length - 1];
    }

    public static LocalDate getFirstDayOfMonth(Integer year, Integer month) {
        if (year == null || month == null) {
            return null;
        }
        return LocalDate.of(year, month, 1);
    }

    public static LocalDate getFirstDayOfMonth(LocalDate currentDate) {
        if (currentDate == null) {
            return null;
        }
        return currentDate.withDayOfMonth(1);
    }

    public static LocalDate getLastDayOfMonth(Integer year, Integer month) {
        if (year == null || month == null) {
            return null;
        }
        return LocalDate.of(year, month, 1).plusMonths(1).minusDays(1);
    }

    public static LocalDate getLastDayOfMonth(LocalDate currentDate) {
        if (currentDate == null) {
            return null;
        }
        return currentDate.withDayOfMonth(1).plusMonths(1).minusDays(1);
    }

    public static LocalDate getFirstDayNextMonth(LocalDate date) {
        if (date == null) {
            return null;
        }
        return date.plusMonths(1).withDayOfMonth(1);
    }

    public static LocalDate getFirstDayOfYear(Integer billingYear) {
        return getFirstDayOfMonth(billingYear, 1);
    }

    public static LocalDate getLastDayOfYear(Integer billingYear) {
        return getLastDayOfMonth(billingYear, 12);
    }

    public static LocalDateTime createLocalDateAtStartOfDay(LocalDate date) {
        if (date == null) {
            return null;
        }
        return date.atStartOfDay();
    }

    public static LocalDateTime createLocalDateAtEndOfDay(LocalDate date) {
        if (date == null) {
            return null;
        }
        return date.atStartOfDay().plusHours(23).plusMinutes(59).plusSeconds(59);
    }

    public static LocalDate createLocalDate(Integer year, Integer month, Integer day) {
        if (year == null || month == null || day == null) {
            return null;
        }

        try {
            return LocalDate.of(year, month, day);

        } catch (DateTimeException exception) {

            return null;
        }
    }

    public static Timestamp createTimestamp(Instant instant) {
        if (instant == null) {
            return null;
        }
        return Timestamp.from(instant);
    }

    public static Calendar createUtcCalendarFromMskStartOfDay(LocalDate date) {
        if (date == null) {
            return null;
        }
        Instant instant = createInstantFromMskStartOfDay(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(UTC_TIME_ZONE);
        calendar.setTimeInMillis(instant.toEpochMilli());
        return calendar;
    }

    public static Calendar createUtcCalendarFromMskEndOfDay(LocalDate date) {
        if (date == null) {
            return null;
        }
        Instant instant = createInstantFromMskEndOfDay(date);
        Calendar calendar = Calendar.getInstance(UTC_TIME_ZONE);
        calendar.setTimeInMillis(instant.toEpochMilli());
        return calendar;
    }
}
