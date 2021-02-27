package ru.otus.homework.settings.xml.dao;

import ru.otus.homework.settings.xml.cvs.CVSRow;
import ru.otus.homework.settings.xml.domain.PersonTest;

import java.util.List;

public interface PersonTestDAO {

    PersonTest getPersonTest(List<CVSRow> rows);

    void printTest(PersonTest personTest);
}
