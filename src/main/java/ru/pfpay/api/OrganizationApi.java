package ru.pfpay.api;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.annotation.ApiQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.pfpay.config.Messages;
import ru.pfpay.domain.Organization;
import ru.pfpay.service.OrganizationService;
import ru.pfpay.service.search.impl.ContractSearch;
import ru.pfpay.service.search.impl.OrganizationSearch;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@Api(name = Messages.API_ORGANIZATION, description = Messages.DESCRIPTION_ORGANIZATION_API)
public class OrganizationApi extends CommonApi {

    private static final String HAS_ROLE_ORGANIZATION_VIEW = "hasRole('ROLE_ORGANIZATION_VIEW')";
    private static final String HAS_ROLE_ORGANIZATION_SAVE = "hasRole('ROLE_ORGANIZATION_SAVE')";
    private static final String HAS_ROLE_ORGANIZATION_DELETE = "hasRole('ROLE_ORGANIZATION_DELETE')";

    private static final String PATH_ORGANIZATION = "/organization";
    private static final String PATH_ORGANIZATION_ID = "/organization/{id}";
    private static final String PATH_ORGANIZATION_REF = "/organization/ref";
    private static final String PATH_ORGANIZATION_REF_ID = "/organization/ref/{id}";
    private static final String PATH_ORGANIZATION_COUNT = "/organization/count";

    @Autowired
    private OrganizationService organizationService;


    @RequestMapping(value = PATH_ORGANIZATION, method = RequestMethod.GET)
    @PreAuthorize(HAS_ROLE_ORGANIZATION_VIEW)
    @ApiMethod(description = Messages.DESCRIPTION_GET_ORGANIZATION_LIST)
    public List<Organization> getOrganizationList(
            @RequestParam(required = false) @ApiQueryParam(name = LIMIT, required = false) Integer limit,
            @RequestParam(required = false) @ApiQueryParam(name = OFFSET, required = false) Integer offset) {

        OrganizationSearch search = new OrganizationSearch();

        search.setOffset(offset);
        search.setLimit(limit);

        return organizationService.getOrganizationList(search);
    }

    @RequestMapping(value = PATH_ORGANIZATION_ID, method = RequestMethod.GET)
    @PreAuthorize(HAS_ROLE_ORGANIZATION_VIEW)
    @ApiMethod(description = Messages.DESCRIPTION_GET_ORGANIZATION)
    public Organization getOrganization(
            @PathVariable @ApiPathParam(name = ID) Long id,
            HttpServletResponse response) {

        return organizationService.getOrganization(id);
    }


    @RequestMapping(value = PATH_ORGANIZATION_COUNT, method = RequestMethod.GET)
    @PreAuthorize(HAS_ROLE_ORGANIZATION_VIEW)
    @ApiMethod(description = Messages.DESCRIPTION_GET_ORGANIZATION_COUNT)
    public Long getOrganizationCount() {

        ContractSearch search = new ContractSearch();

        return organizationService.getOrganizationCount(search);
    }
}
