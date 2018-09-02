package ru.pfpay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pfpay.domain.Organization;
import ru.pfpay.service.permission.OrganizationPermissionService;
import ru.pfpay.service.persistence.OrganizationPersistence;
import ru.pfpay.service.search.Search;

import java.util.List;
import java.util.function.Consumer;

@Service
@Transactional(rollbackFor = Throwable.class)
public class OrganizationServiceImpl implements OrganizationService {


    @Autowired
    private OrganizationPermissionService organizationPermissionService;

    @Autowired
    private OrganizationPersistence organizationPersistence;


    @Override
    public Organization saveOrganization(Organization organization) {

        organizationPermissionService.checkSaveOrganization(organization).throwException();

        return organizationPersistence.merge(organization);
    }

    @Override
    @Transactional(readOnly = true)
    public Organization findOrganization(String ogrn) {
        return organizationPersistence.findOrganization(ogrn);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Organization> getOrganizationList(Search search) {
        return organizationPersistence.list(search);
    }

    @Override
    @Transactional(readOnly = true)
    public Long getOrganizationCount(Search search) {
        return organizationPersistence.count(search);
    }

    @Override
    @Transactional(readOnly = true)
    public Organization getOrganization(Long id) {
        return organizationPersistence.get(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Organization getOrganization(Long id, Consumer<Long> notFoundHandler) {
        return organizationPersistence.get(id, notFoundHandler);
    }

}
