package ru.pfpay.service;

import org.springframework.util.concurrent.ListenableFutureTask;
import ru.pfpay.domain.Request;

public interface TaskFactory {

    ListenableFutureTask<Request> getSendRequestTask(Request request);

    ListenableFutureTask<Request> getSendRequestTask(Request request, String host, Integer port);

    ListenableFutureTask<Request> getCreateContractTask(Request request);

    ListenableFutureTask<Request> getApproveContractTask(Request request);

    ListenableFutureTask<Request> getTerminateContractTask(Request request);
}
