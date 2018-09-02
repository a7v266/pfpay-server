package ru.pfpay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import ru.pfpay.config.ExecutorConfig;
import ru.pfpay.domain.Request;
import ru.pfpay.domain.RequestDirection;
import ru.pfpay.domain.RequestStatus;
import ru.pfpay.domain.RequestType;
import ru.pfpay.utils.ObjectUtils;

import java.util.Collection;

@Service
public class RequestControllerImpl implements RequestController {

    @Autowired
    @Qualifier(ExecutorConfig.REQUEST_EXECUTOR)
    private ThreadPoolTaskExecutor requestExecutor;

    @Autowired
    private TaskFactory taskFactory;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private ConfigService configLoader;


    @Override
    public void executeRequest(Collection<Long> ids) {
        ids.forEach(this::executeRequest);
    }

    @Override
    public void executeRequest(Long requestId) {

        Request request = requestService.getRequest(requestId);

        if (request != null) {
            executeRequest(request);
        }
    }


    @Override
    public void executeRequest(Request request) {

        if (ObjectUtils.contained(request.getRequestStatus(), RequestStatus.CREATED, RequestStatus.FAILED)) {

            if (ObjectUtils.equals(request.getRequestDirection(), RequestDirection.OUTGOING)) {

                if (ObjectUtils.equals(request.getRequestType(), RequestType.CREATE_CONTRACT)) {
                    requestExecutor.submit(taskFactory.getSendRequestTask(request, configLoader.getRemoteHost(), configLoader.getRemotePort()));
                    return;
                }

                if (ObjectUtils.equals(request.getRequestType(), RequestType.APPROVE_CONTRACT)) {
                    requestExecutor.submit(taskFactory.getSendRequestTask(request));
                    return;
                }

                if (ObjectUtils.equals(request.getRequestType(), RequestType.TERMINATE_CONTRACT)) {
                    requestExecutor.submit(taskFactory.getSendRequestTask(request));
                    return;
                }
            }

            if (ObjectUtils.equals(request.getRequestDirection(), RequestDirection.INCOMING)) {

                if (ObjectUtils.equals(request.getRequestType(), RequestType.CREATE_CONTRACT)) {
                    requestExecutor.submit(taskFactory.getCreateContractTask(request));
                    return;
                }

                if (ObjectUtils.equals(request.getRequestType(), RequestType.APPROVE_CONTRACT)) {
                    requestExecutor.submit(taskFactory.getApproveContractTask(request));
                    return;
                }

                if (ObjectUtils.equals(request.getRequestType(), RequestType.TERMINATE_CONTRACT)) {
                    requestExecutor.submit(taskFactory.getTerminateContractTask(request));
                }
            }
        }
    }

}
