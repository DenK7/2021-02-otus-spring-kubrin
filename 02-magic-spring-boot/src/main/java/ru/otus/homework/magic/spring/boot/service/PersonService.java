package ru.otus.homework.magic.spring.boot.service;

import ru.otus.homework.magic.spring.boot.domain.Person;

public interface PersonService {
    Person loadQuestionIntoPerson(String lastName, String firstName) ;

    void startTest(Person person);
}
