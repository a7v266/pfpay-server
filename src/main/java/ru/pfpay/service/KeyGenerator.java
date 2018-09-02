package ru.pfpay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class KeyGenerator {

    private static volatile Map<Class<?>, AtomicLong> keyMap;

    @Autowired
    private KeyService keyService;

    @PostConstruct
    public void init() {
        keyMap = keyService.createKeyMap();
    }

    public static <T> void updateGenerator(Class<T> clazz, Long id) {
        if (keyMap != null && keyMap.containsKey(clazz)) {
            keyMap.get(clazz).getAndUpdate(value -> Math.max(id, value));
        }
    }

    public static Long createKey(Class<?> clazz) {
        if (keyMap.containsKey(clazz)) {
            return keyMap.get(clazz).incrementAndGet();
        }
        return null;
    }

}
