package ru.pfpay.service.permission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.pfpay.config.Messages;
import ru.pfpay.domain.ErrorCollector;
import ru.pfpay.domain.Person;
import ru.pfpay.service.persistence.PersonPersistence;
import ru.pfpay.utils.ObjectUtils;
import ru.pfpay.validation.ValidationUtils;


@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
public class PersonPermissionServiceImpl implements PersonPermissionService {

    @Autowired
    private PersonPersistence personPersistence;

    @Override
    public ErrorCollector checkSavePerson(Person person) {

        ErrorCollector errorCollector = new ErrorCollector(Messages.ERROR_SAVE_PERSON_FORMAT, person);

        Person duplicatePerson = personPersistence.findPerson(person.getSnils());

        if (duplicatePerson != null && ObjectUtils.notEquals(person, duplicatePerson)) {
            errorCollector.add(Messages.CAUSE_DUPLICATE_PERSON_FORMAT, duplicatePerson);
        }

        return ValidationUtils.validate(person, errorCollector);
    }
}
