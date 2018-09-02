package ru.pfpay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pfpay.config.Messages;
import ru.pfpay.domain.*;
import ru.pfpay.service.permission.ContractPermissionService;
import ru.pfpay.service.permission.PersonPermissionService;
import ru.pfpay.service.permission.RequestPermissionService;
import ru.pfpay.service.persistence.ContractPersistence;
import ru.pfpay.service.persistence.OrganizationPersistence;
import ru.pfpay.service.persistence.PersonPersistence;
import ru.pfpay.service.persistence.RequestPersistence;
import ru.pfpay.service.search.Search;
import ru.pfpay.utils.CollectionUtils;
import ru.pfpay.utils.ObjectUtils;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;


@Service
@Transactional(rollbackFor = Throwable.class)
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractPermissionService contractPermissionService;

    @Autowired
    private RequestPermissionService requestPermissionService;

    @Autowired
    private PersonPermissionService personPermissionService;

    @Autowired
    private ContractPersistence contractPersistence;

    @Autowired
    private PersonPersistence personPersistence;

    @Autowired
    private RequestPersistence requestPersistence;

    @Autowired
    private OrganizationPersistence organizationPersistence;

    @Autowired
    private ConfigService configService;

    @Override
    @Transactional(readOnly = true)
    public List<Contract> getContractList(Search search) {
        return contractPersistence.list(search);
    }

    @Override
    @Transactional(readOnly = true)
    public Long getContractCount(Search search) {
        return contractPersistence.count(search);
    }

    @Override
    @Transactional(readOnly = true)
    public Contract getContract(Long id) {
        return contractPersistence.get(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isExist() {
        return contractPersistence.isExist(new Search());
    }

    @Override
    public Contract mergeContract(Contract contract) {

        contract.setPerson(personPersistence.get(contract.getPersonId(), personId -> {

            throw new ErrorCollectorException(Messages.ERROR_PERSON_NOT_FOUND_FORMAT, personId);
        }));

        contract.setOrganization(organizationPersistence.get(contract.getOrganizationId(), organizationId -> {

            throw new ErrorCollectorException(Messages.ERROR_ORGANIZATION_NOT_FOUND_FORMAT, organizationId);
        }));

        contractPermissionService.checkSaveContract(contract).throwException();

        return contractPersistence.merge(contract);
    }

    @Override
    public Request createContract(ContractCreator contractCreator) {

        if (configService.isRegulator()) {
            throw new ErrorCollectorException(Messages.ERROR_FUNCTION_NOT_IMPLEMENTED_FOR_REGULATOR);
        }

        Person person = personPersistence.findPerson(contractCreator.getSnils(), snils -> {
            throw new ErrorCollectorException(Messages.ERROR_PERSON_NOT_FOUND_FORMAT, snils);
        });

        Organization currentOrganization = configService.getCurrentOrganization();

        Contract contract = new Contract();

        contract.setPerson(person);
        contract.setOrganization(currentOrganization);
        contract.setContractNumber(contractCreator.getContractNumber());
        contract.setContractDate(contractCreator.getContractDate());
        contract.setContractStatus(ContractStatus.CREATED);
        contract.setContractBalance(BigDecimal.ZERO);

        contractPermissionService.checkSaveContract(contract).throwException();

        contractPersistence.save(contract);

        Request request = new Request();

        request.setContract(contract);
        request.setRequestType(RequestType.CREATE_CONTRACT);
        request.setRequestDirection(RequestDirection.OUTGOING);
        request.setRequestStatus(RequestStatus.CREATED);

        requestPermissionService.checkSaveRequest(request).throwException();

        requestPersistence.save(request);

        return request;
    }

    @Override
    public Collection<Request> createContract(Request request) {

        RequestData requestData = request.getRequestData();

        Person person = personPersistence.findPerson(requestData.getSnils(), snils -> {
            throw new ErrorCollectorException(Messages.ERROR_PERSON_NOT_FOUND_FORMAT, snils);
        });

        Organization organization = organizationPersistence.findOrganization(requestData.getOgrn(), ogrn -> {
            throw new ErrorCollectorException(Messages.ERROR_ORGANIZATION_NOT_FOUND_FORMAT, ogrn);
        });


        Contract currentContract = person.getContract();

        if (currentContract == null) {
            throw new ErrorCollectorException(Messages.ERROR_PERSON_CURRENT_CONTRACT_EMPTY_FORMAT, person);
        }

        BigDecimal personBalance = currentContract.getContractBalance();

        Contract contract = new Contract();

        contract.setPerson(person);
        contract.setOrganization(organization);
        contract.setContractStatus(ContractStatus.APPROVED);
        contract.setContractNumber(requestData.getContractNumber());
        contract.setContractDate(requestData.getContractDate());
        contract.setContractBalance(personBalance);

        contractPermissionService.checkSaveContract(contract).throwException();

        contractPersistence.save(contract);


        currentContract.setContractStatus(ContractStatus.TERMINATED);
        currentContract.setContractBalance(BigDecimal.ZERO);

        contractPermissionService.checkSaveContract(currentContract).throwException();

        contractPersistence.update(currentContract);


        person.setContract(contract);

        personPermissionService.checkSavePerson(person).throwException();

        personPersistence.update(person);


        request.setContract(contract);

        requestPermissionService.checkSaveRequest(request).throwException();

        requestPersistence.update(request);


        Collection<Request> requests = CollectionUtils.createArrayList();

        Organization currentOrganization = configService.getCurrentOrganization();

        if (ObjectUtils.notEquals(currentContract.getOrganization(), currentOrganization)) {

            Request terminateContractRequest = new Request();

            terminateContractRequest.setContract(currentContract);
            terminateContractRequest.setRequestDirection(RequestDirection.OUTGOING);
            terminateContractRequest.setRequestType(RequestType.TERMINATE_CONTRACT);
            terminateContractRequest.setRequestStatus(RequestStatus.CREATED);

            requestPermissionService.checkSaveRequest(terminateContractRequest).throwException();

            requestPersistence.save(terminateContractRequest);

            requests.add(terminateContractRequest);
        }

        if (ObjectUtils.notEquals(contract.getOrganization(), currentOrganization)) {

            Request approveContractRequest = new Request();

            approveContractRequest.setContract(contract);
            approveContractRequest.setRequestDirection(RequestDirection.OUTGOING);
            approveContractRequest.setRequestType(RequestType.APPROVE_CONTRACT);
            approveContractRequest.setRequestStatus(RequestStatus.CREATED);

            requestPermissionService.checkSaveRequest(approveContractRequest).throwException();

            requestPersistence.save(approveContractRequest);

            requests.add(approveContractRequest);
        }

        return requests;
    }

    @Override
    public void approveContract(Request request) {

        RequestData requestData = request.getRequestData();

        Contract contract = contractPersistence.findContract(requestData.getOgrn(), requestData.getContractNumber());

        contract.setContractStatus(ContractStatus.APPROVED);
        contract.setContractBalance(requestData.getContractBalance());

        contractPermissionService.checkSaveContract(contract).throwException();

        contractPersistence.update(contract);

        request.setContract(contract);

        requestPermissionService.checkSaveRequest(request).throwException();

        requestPersistence.update(request);
    }

    @Override
    public void terminateContract(Request request) {

        RequestData requestData = request.getRequestData();

        Contract contract = contractPersistence.findContract(requestData.getOgrn(), requestData.getContractNumber());

        contract.setContractStatus(ContractStatus.TERMINATED);
        contract.setContractBalance(BigDecimal.ZERO);

        contractPermissionService.checkSaveContract(contract).throwException();

        contractPersistence.update(contract);

        request.setContract(contract);

        requestPermissionService.checkSaveRequest(request).throwException();

        requestPersistence.update(request);
    }

}
