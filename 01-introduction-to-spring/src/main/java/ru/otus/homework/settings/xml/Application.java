package ru.otus.homework.settings.xml;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.homework.settings.xml.cvs.CSVWorkImpl;
import ru.otus.homework.settings.xml.cvs.CVSRow;
import ru.otus.homework.settings.xml.dao.PersonTestDAOImpl;
import ru.otus.homework.settings.xml.domain.Person;
import ru.otus.homework.settings.xml.service.PersonService;
import ru.otus.homework.settings.xml.service.PersonServiceImpl;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Application {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");

        File file = context.getResource(context.getBean(CSVWorkImpl.class).getFileName()).getFile();

        List<CVSRow> cvsRows = context.getBean(CSVWorkImpl.class).getCSVRows(file);

        PersonService personService = context.getBean(PersonServiceImpl.class);
        Person person = personService.getPersonByName("Ivanov", "Petr", cvsRows);

        context.getBean(PersonTestDAOImpl.class).printTest(person.getTest());

        context.close();
    }
}
