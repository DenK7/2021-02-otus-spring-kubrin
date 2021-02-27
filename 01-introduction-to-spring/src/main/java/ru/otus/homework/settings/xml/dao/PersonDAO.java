package ru.otus.homework.settings.xml.dao;

import ru.otus.homework.settings.xml.domain.Person;
import ru.otus.homework.settings.xml.domain.PersonTest;

public interface PersonDAO {
    Person getPersonByName (String lastName, String firstName, PersonTest personTest);
}
