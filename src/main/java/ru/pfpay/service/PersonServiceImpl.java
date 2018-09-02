package ru.pfpay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pfpay.config.Messages;
import ru.pfpay.domain.ErrorCollectorException;
import ru.pfpay.domain.Person;
import ru.pfpay.service.permission.PersonPermissionService;
import ru.pfpay.service.persistence.ContractPersistence;
import ru.pfpay.service.persistence.PersonPersistence;
import ru.pfpay.service.search.Search;

import java.util.List;

@Service
@Transactional(rollbackFor = Throwable.class)
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonPermissionService personPermissionService;

    @Autowired
    private PersonPersistence personPersistence;

    @Autowired
    private ContractPersistence contractPersistence;

    @Override
    @Transactional(readOnly = true)
    public List<Person> getPersonList(Search search) {

        return personPersistence.list(search);
    }

    @Override
    @Transactional(readOnly = true)
    public Person getPerson(Long id) {

        return personPersistence.get(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Long getPersonCount(Search search) {

        return personPersistence.count(search);
    }

    @Override
    @Transactional(readOnly = true)
    public Person findPerson(String snils) {

        return personPersistence.findPerson(snils);
    }


    @Override
    public Person mergePerson(Person person) {

        person.setContract(contractPersistence.get(person.getContractId(), contractId -> {

            throw new ErrorCollectorException(Messages.ERROR_CONTRACT_NOT_FOUND_FORMAT, contractId);
        }));

        personPermissionService.checkSavePerson(person).throwException();

        return personPersistence.merge(person);
    }
}
