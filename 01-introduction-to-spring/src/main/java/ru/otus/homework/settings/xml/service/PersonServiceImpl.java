package ru.otus.homework.settings.xml.service;

import ru.otus.homework.settings.xml.cvs.CVSRow;
import ru.otus.homework.settings.xml.dao.PersonDAO;
import ru.otus.homework.settings.xml.dao.PersonTestDAO;
import ru.otus.homework.settings.xml.domain.Person;
import ru.otus.homework.settings.xml.domain.PersonTest;

import java.util.List;

public class PersonServiceImpl implements PersonService{
    private final PersonDAO personDAO;
    private final PersonTestDAO personTestDAO;

    public PersonServiceImpl(PersonDAO personDAO, PersonTestDAO personTestDAO) {
        this.personDAO = personDAO;
        this.personTestDAO = personTestDAO;
    }

    @Override
    public Person getPersonByName(String lastName, String firstName, List<CVSRow> cvsRows) {
        PersonTest personTest = personTestDAO.getPersonTest(cvsRows);
        return personDAO.getPersonByName(lastName, firstName, personTest);
    }


}
