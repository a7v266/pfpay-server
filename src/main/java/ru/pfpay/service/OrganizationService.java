package ru.pfpay.service;

import org.springframework.transaction.annotation.Transactional;
import ru.pfpay.domain.Organization;
import ru.pfpay.service.search.Search;

import java.util.List;
import java.util.function.Consumer;

public interface OrganizationService {

    List<Organization> getOrganizationList(Search search);

    Long getOrganizationCount(Search search);

    Organization getOrganization(Long id);

    Organization saveOrganization(Organization organization);

    Organization findOrganization(String ogrn);

    @Transactional(readOnly = true)
    Organization getOrganization(Long id, Consumer<Long> notFoundHandler);
}
