package ru.otus.homework.magic.spring.boot.dao;

import ru.otus.homework.magic.spring.boot.domain.Person;
import ru.otus.homework.magic.spring.boot.domain.PersonTest;

public interface PersonDAO {
    void getPersonWithQuestions(Person person, PersonTest personTest);

    Person getPerson(String lastName, String firstName);
}
