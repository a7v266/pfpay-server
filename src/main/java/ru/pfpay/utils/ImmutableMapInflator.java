package ru.pfpay.utils;

import com.google.common.collect.ImmutableMap;

public class ImmutableMapInflator<K, V> {

    private final ImmutableMap.Builder<K, V> builder = ImmutableMap.builder();

    public ImmutableMapInflator<K, V> putValueForKeys(V value, K... keys) {
        for (K key : keys) {
            builder.put(key, value);
        }
        return this;
    }

    public ImmutableMap<K, V> build() {
        return builder.build();
    }
}