package ru.pfpay.utils;

import java.util.HashMap;
import java.util.Map;

public class MapUtils {

    public static <K, V> Map<K, V> createHashMap() {
        return new HashMap<>();
    }

    public static <K> boolean containsKey(K key, Map<K, ?> map) {

        return key != null && map.containsKey(key);
    }

    public static <K, V> V getValue(K key, Map<K, V> map) {

        if (containsKey(key, map)) {
            return map.get(key);
        }

        return null;
    }
}
