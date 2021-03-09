package ru.otus.homework.settings.xml.dao;

import ru.otus.homework.settings.xml.domain.Person;
import ru.otus.homework.settings.xml.domain.PersonTest;

import java.io.IOException;

public interface PersonDAO {
    Person getPersonByName (PersonTest personTest) throws IOException;
}
