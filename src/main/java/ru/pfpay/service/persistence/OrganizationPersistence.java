package ru.pfpay.service.persistence;


import ru.pfpay.domain.Organization;

import java.util.function.Consumer;

public interface OrganizationPersistence extends BasePersistence<Organization, Long> {

    Organization findOrganization(String ogrn);

    Organization findOrganization(String ogrn, Consumer<String> notFoundConsumer);
}
