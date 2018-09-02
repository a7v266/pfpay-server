package ru.pfpay.utils;

public class ClassUtils {


    public static boolean instanceOf(Object[] array, Class<?> clazz) {
        for (Object item : array) {
            if (item == null || !item.getClass().equals(clazz)) {
                return false;
            }
        }
        return true;
    }
}
