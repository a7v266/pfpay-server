package ru.pfpay.utils;

import ru.pfpay.domain.BaseEntity;
import ru.pfpay.domain.Identifiable;

import java.util.Collection;

public class ObjectUtils {

    public static boolean notEquals(Object object1, Object object2) {
        return !equals(object1, object2);
    }

    public static boolean equals(Object object1, Object object2) {
        if (object1 == object2) {
            return true;
        }
        if (object1 == null || object2 == null) {
            return false;
        }
        return object1.equals(object2);
    }

    public static boolean comparable(Object object1, Object object2) {
        if (object1 == null) {
            return false;
        }
        if (object2 == null) {
            return false;
        }
        if (object1.equals(object2)) {
            return false;
        }
        return true;
    }

    public static Long getEntityId(Identifiable object) {
        if (object != null) {
            return object.getId();
        }
        return null;
    }


    public static Long getEntityId(Object object) {
        if (object instanceof BaseEntity) {
            return ((BaseEntity) object).getId();
        }
        return null;
    }

    public static String getClassName(Object object) {
        if (object != null) {
            return object.getClass().getSimpleName();
        }
        return null;
    }

    public static boolean isEmpty(Object object) {

        if (object == null) {
            return true;
        }

        if (object instanceof String) {
            return ((String) object).isEmpty();
        }

        if (object instanceof Object[]) {
            return ((Object[]) object).length == 0;
        }

        if (object instanceof Collection) {
            return ((Collection) object).isEmpty();
        }

        return false;
    }

    public static <T> boolean isNotEmpty(T item) {
        return !isEmpty(item);
    }

    public static boolean contained(Integer value, Integer... items) {

        for (Integer item : items) {

            if (ObjectUtils.equals(item, value)) {
                return true;
            }
        }

        return false;
    }


    public static boolean notContained(Integer value, Integer... items) {

        return !contained(value, items);
    }
}

