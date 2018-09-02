package ru.pfpay.service;

import ru.pfpay.domain.Person;
import ru.pfpay.service.search.Search;

import java.util.List;

public interface PersonService {

    Person findPerson(String snils);

    Person mergePerson(Person person);

    List<Person> getPersonList(Search search);

    Person getPerson(Long id);

    Long getPersonCount(Search search);
}
