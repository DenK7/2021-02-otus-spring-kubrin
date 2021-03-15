package ru.otus.homework.magic.spring.boot.dao;

import ru.otus.homework.magic.spring.boot.domain.PersonTest;

import java.io.IOException;

public interface PersonTestDAO {

    PersonTest getPersonTest() throws IOException;
}
