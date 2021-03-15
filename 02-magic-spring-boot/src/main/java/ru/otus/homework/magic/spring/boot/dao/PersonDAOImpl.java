package ru.otus.homework.magic.spring.boot.dao;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.otus.homework.magic.spring.boot.domain.Person;
import ru.otus.homework.magic.spring.boot.domain.PersonTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

@Component("PersonDAO")
public class PersonDAOImpl implements PersonDAO {

    private MessageSource messageSource;

    public PersonDAOImpl(MessageSource messageSource) {
        this.messageSource = messageSource;
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

        System.out.println(messageSource.getMessage("ENTER.LAST.NAME", null, Locale.getDefault()) );
        String lastName = reader.readLine();
        System.out.println(messageSource.getMessage("ENTER.FIRST.NAME", null, Locale.getDefault()));
        String firstName = reader.readLine();

        person.setLastName(lastName);
        person.setFirstName(firstName);

        return person;
    }
}
