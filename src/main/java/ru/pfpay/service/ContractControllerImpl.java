package ru.pfpay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pfpay.domain.Contract;
import ru.pfpay.domain.ContractCreator;
import ru.pfpay.domain.Request;

import java.time.LocalDate;

@Service
public class ContractControllerImpl implements ContractController {

    @Autowired
    private ContractService contractService;


    @Autowired
    private RequestController requestController;


    @Override
    public Contract createContract(ContractCreator contractCreator) {

        Request request = contractService.createContract(contractCreator);

        requestController.executeRequest(request);

        return request.getContract();
    }
}
