package ru.pfpay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.pfpay.domain.Request;

import java.util.Collection;


@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CreateContractTask extends RequestTask {

    @Autowired
    private ContractService contractService;

    @Autowired
    private RequestController requestController;

    @Override
    public void executeRequest(Request request) {

        Collection<Request> requests = contractService.createContract(request);

        requests.forEach(requestController::executeRequest);
    }

}
