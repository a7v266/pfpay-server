package ru.pfpay.service;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.stereotype.Service;
import ru.pfpay.domain.RequestData;
import ru.pfpay.domain.ResponseData;
import ru.pfpay.utils.StringUtils;

import java.rmi.RemoteException;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RequestProxyServerImpl extends RmiProxyFactoryBean implements RequestProxyServer {

    private static final String URL_FORMAT = "rmi://%s:%d/%s";


    public RequestProxyServerImpl() {
    }

    public RequestProxyServerImpl(String host, Integer port) {
        setServiceInterface(RequestServer.class);
        setServiceUrl(StringUtils.format(URL_FORMAT, host, port, RequestServer.class.getSimpleName()));
    }

    @Override
    public ResponseData submitRequest(RequestData requestData) throws RemoteException {

        return ((RequestServer) getObject()).submitRequest(requestData);
    }
}
