package ru.pfpay.service.permission;

import ru.pfpay.domain.Contract;
import ru.pfpay.domain.ErrorCollector;

public interface ContractPermissionService {

    ErrorCollector checkSaveContract(Contract contract);

    ErrorCollector checkDeleteContract(Contract contract);

}
