package ru.pfpay.api;

import org.jsondoc.core.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.pfpay.config.Messages;
import ru.pfpay.domain.Contract;
import ru.pfpay.domain.ContractCreator;
import ru.pfpay.service.ContractController;
import ru.pfpay.service.ContractService;
import ru.pfpay.service.search.impl.ContractSearch;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@Api(name = Messages.API_CONTRACT, description = Messages.DESCRIPTION_TRANSACTION_API)
public class ContractApi extends CommonApi {

    private static final String HAS_ROLE_CONTRACT_VIEW = "hasRole('ROLE_CONTRACT_VIEW')";
    private static final String HAS_ROLE_CONTRACT_SAVE = "hasRole('ROLE_CONTRACT_SAVE')";
    private static final String HAS_ROLE_CONTRACT_DELETE = "hasRole('ROLE_CONTRACT_DELETE')";

    private static final String PATH_CONTRACT = "/contract";
    private static final String PATH_CONTRACT_ID = "/contract/{id}";
    private static final String PATH_CONTRACT_COUNT = "/contract/count";
    private static final String PATH_CONTRACT_CREATE = "/contract/create";

    @Autowired
    private ContractController contractController;

    @Autowired
    private ContractService contractService;

    @RequestMapping(value = PATH_CONTRACT, method = RequestMethod.GET)
    @PreAuthorize(HAS_ROLE_CONTRACT_VIEW)
    @ApiMethod(description = Messages.DESCRIPTION_GET_CONTRACT_LIST)
    public List<Contract> getContractList(
            @RequestParam(required = false) @ApiQueryParam(name = LIMIT, required = false) Integer limit,
            @RequestParam(required = false) @ApiQueryParam(name = OFFSET, required = false) Integer offset,
            @RequestParam(required = false) @ApiQueryParam(name = PERSON_ID, required = false) Long personId,
            @RequestParam(required = false) @ApiQueryParam(name = ORGANIZATION_ID, required = false) Long organizationId) throws IOException {

        ContractSearch search = new ContractSearch();

        search.setOffset(offset);
        search.setLimit(limit);

        search.setPersonId(personId);
        search.setOrganizationId(organizationId);

        return contractService.getContractList(search);
    }


    @RequestMapping(value = PATH_CONTRACT_ID, method = RequestMethod.GET)
    @PreAuthorize(HAS_ROLE_CONTRACT_VIEW)
    @ApiMethod(description = Messages.DESCRIPTION_GET_CONTRACT)
    public Contract getContract(
            @PathVariable @ApiPathParam(name = ID) Long id,
            HttpServletResponse response) {

        return contractService.getContract(id);
    }

    @RequestMapping(value = PATH_CONTRACT_COUNT, method = RequestMethod.GET)
    @PreAuthorize(HAS_ROLE_CONTRACT_VIEW)
    @ApiMethod(description = Messages.DESCRIPTION_GET_CONTRACT_LIST)
    public Long getContractCount(
            @RequestParam(required = false) @ApiQueryParam(name = PERSON_ID, required = false) Long personId,
            @RequestParam(required = false) @ApiQueryParam(name = ORGANIZATION_ID, required = false) Long organizationId) {

        ContractSearch search = new ContractSearch();

        search.setPersonId(personId);
        search.setOrganizationId(organizationId);

        return contractService.getContractCount(search);
    }


    @RequestMapping(value = PATH_CONTRACT_CREATE, method = RequestMethod.POST)
    @PreAuthorize(HAS_ROLE_CONTRACT_SAVE)
    @ApiMethod(description = Messages.DESCRIPTION_SAVE_CONTRACT)
    public Contract createContract(
            @RequestBody @Valid @ApiBodyObject ContractCreator contractCreator) {

        return contractController.createContract(contractCreator);
    }
}
