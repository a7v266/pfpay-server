package ru.pfpay.service.persistence;

import org.springframework.stereotype.Repository;
import ru.pfpay.domain.Contract;
import ru.pfpay.domain.Organization;
import ru.pfpay.service.search.Field;
import ru.pfpay.service.search.Filter;
import ru.pfpay.service.search.Search;


@Repository
public class ContractPersistenceImpl extends BasePersistenceImpl<Contract, Long> implements ContractPersistence {

    public ContractPersistenceImpl() {
        super(Contract.class);
    }

    @Override
    public Contract findContract(String ogrn, String contractNumber) {

        Search search = new Search();
        search.addFilter(Filter.eq(Field.path(Contract.ORGANIZATION, Organization.OGRN), ogrn));
        search.addFilter(Filter.eq(Contract.CONTRACT_NUMBER, contractNumber));

        return uniqueResult(search);
    }

}
