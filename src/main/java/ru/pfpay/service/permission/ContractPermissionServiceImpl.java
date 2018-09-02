package ru.pfpay.service.permission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.pfpay.config.Messages;
import ru.pfpay.domain.Contract;
import ru.pfpay.domain.ErrorCollector;
import ru.pfpay.service.persistence.ContractPersistence;
import ru.pfpay.utils.ObjectUtils;
import ru.pfpay.validation.ValidationUtils;


@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
public class ContractPermissionServiceImpl implements ContractPermissionService {

    @Autowired
    private ContractPersistence contractPersistence;

    @Override
    public ErrorCollector checkSaveContract(Contract contract) {

        ErrorCollector errorCollector = new ErrorCollector(Messages.ERROR_SAVE_CONTRACT_FORMAT, contract);

        Contract duplicateContract = contractPersistence.findContract(contract.getOgrn(), contract.getContractNumber());

        if (duplicateContract != null && ObjectUtils.notEquals(contract, duplicateContract)) {
            errorCollector.add(Messages.CAUSE_DUPLICATE_CONTRACT_FORMAT, duplicateContract);
        }

        return ValidationUtils.validate(contract, errorCollector);
    }

    @Override
    public ErrorCollector checkDeleteContract(Contract contract) {

        ErrorCollector errorCollector = new ErrorCollector(Messages.ERROR_DELETE_CONTRACT_FORMAT, contract);

        errorCollector.add(Messages.CAUSE_OPERATION_NOT_SUPPORTED);

        return errorCollector;
    }
}
