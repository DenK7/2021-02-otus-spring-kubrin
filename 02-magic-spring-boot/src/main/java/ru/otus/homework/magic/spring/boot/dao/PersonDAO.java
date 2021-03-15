package ru.otus.homework.magic.spring.boot.dao;

import ru.otus.homework.magic.spring.boot.domain.Person;
import ru.otus.homework.magic.spring.boot.domain.PersonTest;

import java.io.IOException;

public interface PersonDAO {
    Person getPersonByName (PersonTest personTest) throws IOException;
}
