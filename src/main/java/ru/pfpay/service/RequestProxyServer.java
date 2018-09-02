package ru.pfpay.service;

import ru.pfpay.domain.RequestData;
import ru.pfpay.domain.ResponseData;

import java.rmi.RemoteException;

public interface RequestProxyServer {

    ResponseData submitRequest(RequestData requestData) throws RemoteException;
}
