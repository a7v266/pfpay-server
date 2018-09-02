package ru.pfpay.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.rmi.RmiServiceExporter;
import org.springframework.stereotype.Service;
import ru.pfpay.config.Loggers;
import ru.pfpay.domain.ErrorCode;
import ru.pfpay.domain.Request;
import ru.pfpay.domain.RequestData;
import ru.pfpay.domain.ResponseData;
import ru.pfpay.domain.RequestDirection;
import ru.pfpay.domain.RequestStatus;

import javax.annotation.PostConstruct;

@Service
public class RequestServerImpl extends RmiServiceExporter implements RequestServer {

    private static final String PROPERTY_SERVER_HOSTNAME = "java.rmi.server.hostname";

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private RequestController requestController;

    @Autowired
    private ConfigService configLoader;

    @PostConstruct
    public void init() {

        System.setProperty(PROPERTY_SERVER_HOSTNAME, configLoader.getLocalHost());
        setRegistryPort(configLoader.getLocalPort());

        setService(this);
        setServiceInterface(RequestServer.class);
        setServiceName(RequestServer.class.getSimpleName());
        setAlwaysCreateRegistry(true);
    }


    @Override
    public ResponseData submitRequest(RequestData requestData) {

        ResponseData responseData = new ResponseData();

        try {

            Request request = new Request();

            request.setRequestStatus(RequestStatus.CREATED);
            request.setRequestDirection(RequestDirection.INCOMING);
            request.setRequestType(requestData.getRequestType());
            request.setRequestData(requestData);

            requestService.saveRequest(request);

            requestController.executeRequest(request);


        } catch (Exception exception) {

            Loggers.REQUEST_LOGGER.error(exception.getMessage(), exception);

            responseData.setErrorCode(ErrorCode.SERVER_ERROR_CODE);
            responseData.setErrorMessage(exception.getMessage());

            throw exception;

        }

        return responseData;
    }
}
