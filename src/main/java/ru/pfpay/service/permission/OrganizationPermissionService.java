package ru.pfpay.service.permission;

import ru.pfpay.domain.ErrorCollector;
import ru.pfpay.domain.Organization;

public interface OrganizationPermissionService {
    ErrorCollector checkSaveOrganization(Organization organization);
}
