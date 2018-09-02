package ru.pfpay.service;

import ru.pfpay.domain.Contract;
import ru.pfpay.domain.ContractCreator;

public interface ContractController {

    Contract createContract(ContractCreator contractCreator);
}
