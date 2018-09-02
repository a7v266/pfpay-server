package ru.pfpay.utils;

import ru.pfpay.config.Format;

public class ArrayUtils {

    public static <T> boolean isEmpty(T[] items) {
        return items == null || items.length == 0;
    }

    public static <T> boolean isNotEmpty(T... items) {
        return items != null && items.length > 0;
    }

    public static <T> T firstNotNull(T... items) {
        for (T item : items) {
            if (item != null) {
                return item;
            }
        }
        return null;
    }

    public static String firstNotEmpty(String... items) {
        for (String item : items) {
            if (item != null && item.length() > 0) {
                return item;
            }
        }
        return Format.EMPTY_STRING;
    }

    public static <T> boolean isNotNull(T... items) {
        for (T item : items) {
            if (item == null) {
                return false;
            }
        }
        return true;
    }
}
