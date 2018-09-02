package ru.pfpay.service.permission;

import ru.pfpay.domain.ErrorCollector;
import ru.pfpay.domain.Person;

public interface PersonPermissionService {
    ErrorCollector checkSavePerson(Person person);
}
