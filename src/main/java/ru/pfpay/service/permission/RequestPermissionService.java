package ru.pfpay.service.permission;

import ru.pfpay.domain.ErrorCollector;
import ru.pfpay.domain.Request;

public interface RequestPermissionService {

    ErrorCollector checkSaveRequest(Request request);
}
