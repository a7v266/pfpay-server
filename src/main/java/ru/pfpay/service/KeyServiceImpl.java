package ru.pfpay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pfpay.domain.Contract;
import ru.pfpay.domain.Request;
import ru.pfpay.service.persistence.ContractPersistence;
import ru.pfpay.service.persistence.RequestPersistence;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
@Transactional(readOnly = true)
public class KeyServiceImpl implements KeyService {

    @Autowired
    private ContractPersistence contractPersistence;

    @Autowired
    private RequestPersistence requestPersistence;

    @Override
    public Map<Class<?>, AtomicLong> createKeyMap() {

        Map<Class<?>, AtomicLong> keyMap = new ConcurrentHashMap<>();

        keyMap.put(Contract.class, new AtomicLong(contractPersistence.getMaxId()));
        keyMap.put(Request.class, new AtomicLong(requestPersistence.getMaxId()));

        return keyMap;
    }
}
