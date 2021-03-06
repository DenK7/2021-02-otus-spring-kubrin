package ru.otus.homework.magic.spring.boot.dao;

import org.springframework.stereotype.Component;
import ru.otus.homework.magic.spring.boot.domain.Person;
import ru.otus.homework.magic.spring.boot.domain.PersonTest;
import ru.otus.homework.magic.spring.boot.exception.QuestionsNotFound;

@Component("PersonDAO")
public class PersonDAOImpl implements PersonDAO {

    @Override
    public void getPersonWithQuestions(Person person, PersonTest personTest) {
        if (personTest.getQuestions() == null || personTest.getQuestions().size() == 0) {
            throw new QuestionsNotFound();
        }
        person.setTest(personTest);
    }

    @Override
    public Person getPerson(String lastName, String firstName) {
        Person person = new Person();

        person.setLastName(lastName);
        person.setFirstName(firstName);

        return person;
    }
}
