package ru.pfpay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pfpay.config.Messages;
import ru.pfpay.domain.ErrorCollectorException;
import ru.pfpay.domain.Request;
import ru.pfpay.domain.RequestStatus;
import ru.pfpay.service.permission.RequestPermissionService;
import ru.pfpay.service.persistence.ContractPersistence;
import ru.pfpay.service.persistence.RequestPersistence;
import ru.pfpay.service.search.impl.RequestSearch;

import java.time.Instant;
import java.util.List;

@Service
@Transactional(rollbackFor = Throwable.class)
public class RequestServiceImpl implements RequestService {

    @Value("${request.timeout}")
    private int requestTimeout;

    @Autowired
    private RequestPermissionService requestPermissionService;

    @Autowired
    private RequestPersistence requestPersistence;

    @Autowired
    private ContractPersistence contractPersistence;

    @Override
    public Request saveRequest(Request request) {

        request.setContract(contractPersistence.get(request.getContractId()));

        requestPermissionService.checkSaveRequest(request).throwException();

        return requestPersistence.merge(request);
    }

    @Override
    public void updateRequest(Request request) {

        request.setContract(contractPersistence.get(request.getContractId()));

        requestPermissionService.checkSaveRequest(request).throwException();

        requestPersistence.update(request);
    }

    @Override
    public Request lockRequest(Long requestId) {

        Instant currentTime = Instant.now();

        Request request = requestPersistence.get(requestId, id -> {

            throw new ErrorCollectorException(Messages.ERROR_REQUEST_NOT_FOUND_FORMAT, id);
        });

        if (request.getLockTime() == null || request.getLockTime().plusSeconds(requestTimeout).isBefore(currentTime)) {

            request.setLockTime(currentTime);
            request.setExecutionTime(currentTime);
            request.setRequestStatus(RequestStatus.PERFORMED);
            request.setRequestDuration(null);
            request.setErrorMessage(null);

            requestPersistence.update(request);

            return request;
        }

        return null;
    }


    @Override
    @Transactional(readOnly = true)
    public Request getRequest(Long requestId) {
        return requestPersistence.get(requestId);
    }

    @Override
    @Transactional(readOnly = true)
    public Long getRequestCount(RequestSearch search) {
        return requestPersistence.count(search);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Request> getRequestList(RequestSearch search) {
        return requestPersistence.list(search);
    }
}
