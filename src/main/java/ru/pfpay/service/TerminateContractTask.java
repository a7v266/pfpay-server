package ru.pfpay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.pfpay.domain.Request;


@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TerminateContractTask extends RequestTask {

    @Autowired
    private ContractService contractService;


    @Override
    public void executeRequest(Request request) throws Exception {

        contractService.terminateContract(request);
    }

}
