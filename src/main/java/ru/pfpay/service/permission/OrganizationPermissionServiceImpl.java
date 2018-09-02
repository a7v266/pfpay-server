package ru.pfpay.service.permission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.pfpay.config.Messages;
import ru.pfpay.domain.ErrorCollector;
import ru.pfpay.domain.Organization;
import ru.pfpay.service.persistence.OrganizationPersistence;
import ru.pfpay.utils.ObjectUtils;
import ru.pfpay.validation.ValidationUtils;


@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
public class OrganizationPermissionServiceImpl implements OrganizationPermissionService {

    @Autowired
    private OrganizationPersistence organizationPersistence;

    @Override
    public ErrorCollector checkSaveOrganization(Organization organization) {

        ErrorCollector errorCollector = new ErrorCollector(Messages.ERROR_SAVE_ORGANIZATION_FORMAT, organization);

        Organization duplicateOrganization = organizationPersistence.findOrganization(organization.getOgrn());

        if (duplicateOrganization != null && ObjectUtils.notEquals(organization, duplicateOrganization)) {
            errorCollector.add(Messages.CAUSE_DUPLICATE_PERSON_FORMAT, duplicateOrganization);
        }

        return ValidationUtils.validate(organization, errorCollector);
    }
}
