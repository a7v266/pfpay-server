package ru.pfpay.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.pfpay.config.Loggers;
import ru.pfpay.domain.ErrorCode;
import ru.pfpay.domain.Request;
import ru.pfpay.domain.RequestStatus;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Callable;


public abstract class RequestTask implements Callable<Request> {

    @Autowired
    private RequestService requestService;

    private Long requestId;

    protected abstract void executeRequest(Request request) throws Exception;

    @Override
    public Request call() {

        Request request = requestService.lockRequest(requestId);

        try {

            executeRequest(request);

            request.setRequestStatus(RequestStatus.COMPLETED);
            request.setErrorMessage(null);

        } catch (Exception exception) {

            Loggers.REQUEST_LOGGER.error(request.toString(), exception);

            request.setRequestStatus(RequestStatus.FAILED);
            request.setErrorCode(ErrorCode.CLIENT_ERROR_CODE);
            request.setErrorMessage(exception.getMessage());

        } finally {

            request.setLockTime(null);
            request.setRequestDuration(Duration.between(request.getExecutionTime(), Instant.now()).getSeconds());
        }

        try {

            requestService.updateRequest(request);

        } catch (Exception exception) {

            Loggers.REQUEST_LOGGER.error(request.toString(), exception);
        }

        return request;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }
}
