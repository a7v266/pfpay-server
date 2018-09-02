package ru.pfpay.service;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public interface KeyService {

    Map<Class<?>, AtomicLong> createKeyMap();
}
