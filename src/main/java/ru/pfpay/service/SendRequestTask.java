package ru.pfpay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.pfpay.domain.Request;
import ru.pfpay.domain.ResponseData;

import java.rmi.RemoteException;


@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SendRequestTask extends RequestTask {

    private String host;

    private Integer port;

    @Autowired
    private ApplicationContext applicationContext;


    @Override
    public void executeRequest(Request request) throws RemoteException {

        RequestProxyServer proxy = applicationContext.getBean(RequestProxyServer.class, host, port);

        ResponseData responseData = proxy.submitRequest(request.createRequestData());

        request.setErrorCode(responseData.getErrorCode());

        request.setErrorMessage(responseData.getErrorMessage());

    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
