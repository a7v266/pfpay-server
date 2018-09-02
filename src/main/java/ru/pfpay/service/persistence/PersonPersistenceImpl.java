package ru.pfpay.service.persistence;

import org.springframework.stereotype.Repository;
import ru.pfpay.domain.Person;
import ru.pfpay.service.search.Filter;
import ru.pfpay.service.search.Search;

import java.util.function.Consumer;


@Repository
public class PersonPersistenceImpl extends BasePersistenceImpl<Person, Long> implements PersonPersistence {

    public PersonPersistenceImpl() {
        super(Person.class);
    }

    @Override
    public Person findPerson(String snils) {

        Search search = new Search();
        search.addFilter(Filter.eq(Person.SNILS, snils));

        return uniqueResult(search);
    }

    @Override
    public Person findPerson(String snils, Consumer<String> notFoundConsumer) {

        Person person = findPerson(snils);

        if (person == null) {
            notFoundConsumer.accept(snils);
        }

        return person;
    }

}