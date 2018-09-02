package ru.pfpay.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.pfpay.config.Format;

import java.util.Arrays;
import java.util.IllegalFormatException;

public class StringUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(StringUtils.class);


    public static String clean(String source) {
        if (source == null) {
            return null;
        }
        String result = source.trim().replaceAll("\\s+", Format.SPACE);
        if (isEmpty(result)) {
            return null;
        }
        return result;
    }


    public static String cleanSpace(String source) {
        if (source == null) {
            return null;
        }
        return source.replaceAll("\\s+", Format.SPACE);
    }

    public static String trim(String value) {

        if (value == null) {
            return null;
        }

        if (value.isEmpty()) {
            return null;
        }

        return value.trim();
    }

    public static String format(String format, Object... objects) {
        try {
            return String.format(format, objects);

        } catch (IllegalFormatException exception) {

            LOGGER.error(exception.getMessage(), exception);
        }

        return Format.EMPTY_STRING;
    }


    public static String replace(String source, String target, String value) {
        if (source == null || target == null || value == null) {
            return source;
        }
        return source.replace(target, value);
    }


    public static String asTwoDigits(String source) {
        return paddingLeft(source, 2, '0');
    }

    public static String paddingLeft(String source, int length, char paddingChar) {
        if (source == null) {
            return null;
        }
        if (source.length() >= length) {
            return source;
        }
        char[] paddingArray = new char[length - source.length()];
        Arrays.fill(paddingArray, paddingChar);
        return String.valueOf(paddingArray).concat(source);
    }

    public static String cut(String source, int size) {
        if (source == null) {
            return null;
        }
        return source.substring(0, Math.min(source.length(), size));
    }

    public static String valueOf(Object value) {
        if (value == null) {
            return null;
        }
        return String.valueOf(value);
    }

    public static String toString(Object value) {
        if (value == null) {
            return Format.EMPTY_STRING;
        }
        return value.toString();
    }

    public static String tail(String source, int size) {
        if (source == null) {
            return Format.EMPTY_STRING;
        }
        int index = source.length() - size;

        if (index < 0) {
            return Format.EMPTY_STRING;
        }
        return source.substring(index);
    }


    public static boolean isEmpty(String value) {
        if (value == null) {
            return true;
        }
        return value.isEmpty();
    }

    public static boolean isNotEmpty(String value) {
        if (value == null) {
            return false;
        }
        return value.length() > 0;
    }

    public static boolean contains(String value, String[] keys) {
        if (isEmpty(value) || ArrayUtils.isEmpty(keys)) {
            return false;
        }
        for (String key : keys) {
            if (isEmpty(key)) {
                continue;
            }
            if (value.toLowerCase().contains(key.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
