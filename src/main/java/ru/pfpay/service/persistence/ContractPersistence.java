package ru.pfpay.service.persistence;


import ru.pfpay.domain.Contract;

public interface ContractPersistence extends BasePersistence<Contract, Long> {


    Contract findContract(String ogrn, String contractNumber);
}
