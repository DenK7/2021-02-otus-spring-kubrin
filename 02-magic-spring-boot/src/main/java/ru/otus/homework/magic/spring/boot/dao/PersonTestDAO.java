package ru.otus.homework.magic.spring.boot.dao;

import ru.otus.homework.magic.spring.boot.domain.PersonTest;
import ru.otus.homework.magic.spring.boot.exception.LoadFileException;
import ru.otus.homework.magic.spring.boot.exception.ParsException;

import java.io.IOException;

public interface PersonTestDAO {

    PersonTest getPersonTest() throws IOException, LoadFileException, ParsException;
}
