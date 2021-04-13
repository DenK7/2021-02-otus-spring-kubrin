package ru.otus.homework.magic.spring.boot.dao;

import org.springframework.stereotype.Component;
import ru.otus.homework.magic.spring.boot.domain.Person;
import ru.otus.homework.magic.spring.boot.domain.PersonTest;
import ru.otus.homework.magic.spring.boot.messages.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component("PersonDAO")
public class PersonDAOImpl implements PersonDAO {

    private final Message message;

    public PersonDAOImpl(Message message) {
        this.message = message;
    }

    @Override
    public Person getPersonByName(PersonTest personTest) throws IOException {

        Person person = getPersonName();
        person.setTest(personTest);

        return person;
    }

    public Person getPersonName() throws IOException {
        Person person = new Person();

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        System.out.println(message.getMessage("ENTER.LAST.NAME") );
        String lastName = reader.readLine();
        System.out.println(message.getMessage("ENTER.FIRST.NAME"));
        String firstName = reader.readLine();

        person.setLastName(lastName);
        person.setFirstName(firstName);

        return person;
    }
}
