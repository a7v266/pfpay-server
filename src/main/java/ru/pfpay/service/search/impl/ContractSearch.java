package ru.pfpay.service.search.impl;

import ru.pfpay.domain.Contract;
import ru.pfpay.domain.Organization;
import ru.pfpay.domain.Person;
import ru.pfpay.service.search.Field;
import ru.pfpay.service.search.Filter;
import ru.pfpay.service.search.Search;

public class ContractSearch extends Search {

    public void setPersonId(Long personId) {
        if (personId != null) {
            addFilter(Filter.eq(Field.path(Contract.PERSON, Person.ID), personId));
        }
    }

    public void setOrganizationId(Long organizationId) {
        if (organizationId != null) {
            addFilter(Filter.eq(Field.path(Contract.ORGANIZATION, Organization.ID), organizationId));
        }
    }
}
