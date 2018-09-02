package ru.pfpay.service.persistence;


import ru.pfpay.domain.Person;

import java.util.function.Consumer;

public interface PersonPersistence extends BasePersistence<Person, Long> {

    Person findPerson(String snils);

    Person findPerson(String snils, Consumer<String> notFoundConsumer);
}
