package ru.otus.homework.settings.xml.dao;

import org.springframework.stereotype.Component;
import ru.otus.homework.settings.xml.domain.Person;
import ru.otus.homework.settings.xml.domain.PersonTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component("PersonDAO")
public class PersonDAOImpl implements PersonDAO {

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

        System.out.println("Enter first name: ");
        String lastName = reader.readLine();
        System.out.println("Enter last name: ");
        String firstName = reader.readLine();

        person.setLastName(lastName);
        person.setFirstName(firstName);

        return person;
    }
}
