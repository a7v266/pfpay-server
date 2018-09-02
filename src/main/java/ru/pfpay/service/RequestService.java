package ru.pfpay.service;

import ru.pfpay.domain.Request;
import ru.pfpay.service.search.impl.RequestSearch;

import java.util.List;

public interface RequestService {

    Request saveRequest(Request request);

    void updateRequest(Request request);

    Request lockRequest(Long id);

    Request getRequest(Long id);

    Long getRequestCount(RequestSearch search);

    List<Request> getRequestList(RequestSearch search);
}
