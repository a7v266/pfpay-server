package ru.pfpay.api;

import org.jsondoc.core.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.pfpay.config.Messages;
import ru.pfpay.domain.Ids;
import ru.pfpay.domain.ObjectList;
import ru.pfpay.domain.Request;
import ru.pfpay.service.RequestController;
import ru.pfpay.service.RequestService;
import ru.pfpay.service.search.impl.RequestSearch;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@Api(name = Messages.API_REQUEST, description = Messages.DESCRIPTION_REQUEST_API)
public class RequestApi extends CommonApi {

    private static final String HAS_ROLE_REQUEST_VIEW = "hasRole('ROLE_REQUEST_VIEW')";
    private static final String HAS_ROLE_REQUEST_SAVE = "hasRole('ROLE_REQUEST_SAVE')";
    private static final String HAS_ROLE_REQUEST_EXECUTE = "hasRole('ROLE_REQUEST_EXECUTE')";
    private static final String HAS_ROLE_REQUEST_DELETE = "hasRole('ROLE_REQUEST_DELETE')";

    private static final String PATH_REQUEST = "/request";
    private static final String PATH_REQUEST_ID = "/request/{id}";
    private static final String PATH_REQUEST_COUNT = "/request/count";
    private static final String PATH_REQUEST_EXECUTE = "/request/execute";

    @Autowired
    private RequestService requestService;

    @Autowired
    private RequestController requestController;

    @RequestMapping(value = PATH_REQUEST, method = RequestMethod.GET)
    @PreAuthorize(HAS_ROLE_REQUEST_VIEW)
    @ApiMethod(description = Messages.DESCRIPTION_GET_REQUEST_LIST)
    public ObjectList getRequestList(
            @RequestParam(required = false) @ApiQueryParam(name = LIMIT, required = false) Integer limit,
            @RequestParam(required = false) @ApiQueryParam(name = OFFSET, required = false) Integer offset,
            @RequestParam(required = false) @ApiQueryParam(name = CONTRACT_ID, required = false) Long contractId,
            @RequestParam(required = false) @ApiQueryParam(name = PERSON_ID, required = false) Long personId,
            @RequestParam(required = false) @ApiQueryParam(name = ORGANIZATION_ID, required = false) Long organizationId) {

        RequestSearch search = new RequestSearch();

        search.setOffset(offset);
        search.setLimit(limit);

        search.setContractId(contractId);
        search.setPersonId(personId);
        search.setOrganizationId(organizationId);



        return ObjectList.create(requestService.getRequestList(search), requestService.getRequestCount(search));
    }

    @RequestMapping(value = PATH_REQUEST_ID, method = RequestMethod.GET)
    @PreAuthorize(HAS_ROLE_REQUEST_VIEW)
    @ApiMethod(description = Messages.DESCRIPTION_GET_REQUEST)
    public Request getRequest(
            @PathVariable @ApiPathParam(name = ID) Long id,
            HttpServletResponse response) {

        return requestService.getRequest(id);
    }

    @RequestMapping(value = PATH_REQUEST_COUNT, method = RequestMethod.GET)
    @PreAuthorize(HAS_ROLE_REQUEST_VIEW)
    @ApiMethod(description = Messages.DESCRIPTION_GET_REQUEST_COUNT)
    public Long getRequestCount(
            @RequestParam(required = false) @ApiQueryParam(name = CONTRACT_ID, required = false) Long contractId,
            @RequestParam(required = false) @ApiQueryParam(name = PERSON_ID, required = false) Long personId,
            @RequestParam(required = false) @ApiQueryParam(name = ORGANIZATION_ID, required = false) Long organizationId) {

        RequestSearch search = new RequestSearch();

        search.setContractId(contractId);
        search.setPersonId(personId);
        search.setOrganizationId(organizationId);

        return requestService.getRequestCount(search);
    }


    @RequestMapping(value = PATH_REQUEST_EXECUTE, method = RequestMethod.POST)
    @PreAuthorize(HAS_ROLE_REQUEST_EXECUTE)
    @ApiMethod(description = Messages.DESCRIPTION_EXECUTE_REQUEST)
    public void executeRequest(
            @RequestBody @Valid @ApiBodyObject Ids ids) {

        requestController.executeRequest(ids.getIds());
    }
}
