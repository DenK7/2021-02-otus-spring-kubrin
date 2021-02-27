package ru.otus.homework.settings.xml.dao;

import ru.otus.homework.settings.xml.domain.Person;
import ru.otus.homework.settings.xml.domain.PersonTest;

public class PersonDAOImpl implements PersonDAO{

    @Override
    public Person getPersonByName(String lastName, String firstName, PersonTest personTest) {
        return Person.builder()
                .lastName(lastName)
                .firstName(firstName)
                .test(personTest)
                .build();
    }
}
