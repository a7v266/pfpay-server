package ru.pfpay.service.persistence;

import org.springframework.stereotype.Repository;
import ru.pfpay.domain.Organization;
import ru.pfpay.service.search.Filter;
import ru.pfpay.service.search.Search;

import java.util.function.Consumer;


@Repository
public class OrganizationPersistenceImpl extends BasePersistenceImpl<Organization, Long> implements OrganizationPersistence {

    public OrganizationPersistenceImpl() {
        super(Organization.class);
    }

    @Override
    public Organization findOrganization(String ogrn) {

        Search search = new Search();
        search.addFilter(Filter.eq(Organization.OGRN, ogrn));

        return uniqueResult(search);
    }

    @Override
    public Organization findOrganization(String ogrn, Consumer<String> notFoundConsumer) {

        Organization organization = findOrganization(ogrn);

        if (organization == null) {
            notFoundConsumer.accept(ogrn);
        }
        return organization;
    }

}
