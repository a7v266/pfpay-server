package ru.pfpay.service;

import ru.pfpay.domain.Contract;
import ru.pfpay.domain.ContractCreator;
import ru.pfpay.domain.Request;
import ru.pfpay.service.search.Search;

import java.util.Collection;
import java.util.List;

public interface ContractService {

    List<Contract> getContractList(Search search);

    Long getContractCount(Search search);

    Contract getContract(Long id);

    Contract mergeContract(Contract contract);

    Request createContract(ContractCreator contractCreator);

    Collection<Request> createContract(Request request);

    void approveContract(Request request);

    void terminateContract(Request request);

    boolean isExist();
}
