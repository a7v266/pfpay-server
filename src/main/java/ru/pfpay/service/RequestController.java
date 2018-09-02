package ru.pfpay.service;

import ru.pfpay.domain.Request;

import java.util.Collection;

public interface RequestController {

    void executeRequest(Collection<Long> ids);

    void executeRequest(Long id);

    void executeRequest(Request request);
}
