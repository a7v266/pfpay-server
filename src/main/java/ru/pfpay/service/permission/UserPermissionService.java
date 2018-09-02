package ru.pfpay.service.permission;

import ru.pfpay.domain.ErrorCollector;
import ru.pfpay.domain.User;

public interface UserPermissionService {
    ErrorCollector checkSaveUser(User user);
}
