package ru.otus.homework.settings.xml;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.homework.settings.xml.dao.PersonTestDAOImpl;
import ru.otus.homework.settings.xml.domain.Person;
import ru.otus.homework.settings.xml.service.PersonService;
import ru.otus.homework.settings.xml.service.PersonServiceImpl;

import java.io.IOException;

public class Application {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");

        PersonService personService = context.getBean(PersonServiceImpl.class);
        Person person = personService.getPersonByName("Ivanov", "Petr");

        context.getBean(PersonTestDAOImpl.class).printTest(person.getTest());

        context.close();
    }
}
