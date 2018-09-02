package ru.pfpay.service;

import ru.pfpay.domain.RequestData;
import ru.pfpay.domain.ResponseData;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RequestServer extends Remote {

    ResponseData submitRequest(RequestData requestData) throws RemoteException;
}
