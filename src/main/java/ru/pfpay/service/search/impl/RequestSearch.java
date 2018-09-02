package ru.pfpay.service.search.impl;

import ru.pfpay.domain.Contract;
import ru.pfpay.domain.Organization;
import ru.pfpay.domain.Person;
import ru.pfpay.domain.Request;
import ru.pfpay.service.search.Field;
import ru.pfpay.service.search.Filter;
import ru.pfpay.service.search.Search;

public class RequestSearch extends Search {

    public void setContractId(Long contractId) {
        if (contractId != null) {
            addFilter(Filter.eq(Field.path(Request.CONTRACT, Contract.ID), contractId));
        }
    }

    public void setPersonId(Long personId) {
        if (personId != null) {
            addFilter(Filter.eq(Field.path(Request.CONTRACT, Contract.PERSON, Person.ID), personId));
        }
    }

    public void setOrganizationId(Long organizationId) {
        if (organizationId != null) {
            addFilter(Filter.eq(Field.path(Request.CONTRACT, Contract.ORGANIZATION, Organization.ID), organizationId));
        }
    }
}
