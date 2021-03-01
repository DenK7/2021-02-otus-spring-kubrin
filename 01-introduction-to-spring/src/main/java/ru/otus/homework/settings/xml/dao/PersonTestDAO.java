package ru.otus.homework.settings.xml.dao;

import ru.otus.homework.settings.xml.domain.PersonTest;

import java.io.IOException;

public interface PersonTestDAO {

    PersonTest getPersonTest() throws IOException;

    void printTest(PersonTest personTest);
}
