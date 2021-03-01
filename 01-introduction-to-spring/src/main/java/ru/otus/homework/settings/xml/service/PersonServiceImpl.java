package ru.otus.homework.settings.xml.service;

import ru.otus.homework.settings.xml.dao.PersonDAO;
import ru.otus.homework.settings.xml.dao.PersonTestDAO;
import ru.otus.homework.settings.xml.domain.Person;
import ru.otus.homework.settings.xml.domain.PersonTest;

import java.io.IOException;

public class PersonServiceImpl implements PersonService{
    private final PersonDAO personDAO;
    private final PersonTestDAO personTestDAO;

    public PersonServiceImpl(PersonDAO personDAO, PersonTestDAO personTestDAO) {
        this.personDAO = personDAO;
        this.personTestDAO = personTestDAO;
    }

    @Override
    public Person getPersonByName(String lastName, String firstName) throws IOException {
        PersonTest personTest = personTestDAO.getPersonTest();
        return personDAO.getPersonByName(lastName, firstName, personTest);
    }


}
