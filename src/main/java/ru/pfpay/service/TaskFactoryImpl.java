package ru.pfpay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureTask;
import ru.pfpay.domain.Request;

@Service
public class TaskFactoryImpl implements TaskFactory {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public ListenableFutureTask<Request> getSendRequestTask(Request request) {

        SendRequestTask task = applicationContext.getBean(SendRequestTask.class);

        task.setRequestId(request.getId());
        task.setHost(request.getHost());
        task.setPort(request.getPort());

        return new ListenableFutureTask<>(task);
    }

    @Override
    public ListenableFutureTask<Request> getSendRequestTask(Request request, String host, Integer port) {

        SendRequestTask task = applicationContext.getBean(SendRequestTask.class);

        task.setRequestId(request.getId());
        task.setHost(host);
        task.setPort(port);

        return new ListenableFutureTask<>(task);
    }


    @Override
    public ListenableFutureTask<Request> getCreateContractTask(Request request) {

        CreateContractTask task = applicationContext.getBean(CreateContractTask.class);

        task.setRequestId(request.getId());

        return new ListenableFutureTask<>(task);
    }

    @Override
    public ListenableFutureTask<Request> getApproveContractTask(Request request) {

        ApproveContractTask task = applicationContext.getBean(ApproveContractTask.class);

        task.setRequestId(request.getId());

        return new ListenableFutureTask<>(task);
    }

    @Override
    public ListenableFutureTask<Request> getTerminateContractTask(Request request) {

        TerminateContractTask task = applicationContext.getBean(TerminateContractTask.class);

        task.setRequestId(request.getId());

        return new ListenableFutureTask<>(task);
    }
}
