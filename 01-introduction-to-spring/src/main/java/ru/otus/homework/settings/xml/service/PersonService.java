package ru.otus.homework.settings.xml.service;

import ru.otus.homework.settings.xml.domain.Person;

import java.io.IOException;

public interface PersonService {
    Person getPersonByName(String lastName, String firstName) throws IOException;
}
