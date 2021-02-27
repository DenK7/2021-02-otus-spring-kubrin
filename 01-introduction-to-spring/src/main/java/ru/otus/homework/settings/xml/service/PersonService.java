package ru.otus.homework.settings.xml.service;

import ru.otus.homework.settings.xml.cvs.CVSRow;
import ru.otus.homework.settings.xml.domain.Person;

import java.util.List;

public interface PersonService {
    Person getPersonByName(String lastName, String firstName, List<CVSRow> cvsRows);
}
