package ru.otus.homework.settings.xml;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.homework.settings.xml.domain.Person;
import ru.otus.homework.settings.xml.service.PersonService;
import ru.otus.homework.settings.xml.service.PersonServiceImpl;

import java.io.IOException;

@Configuration
@ComponentScan
@PropertySource("classpath:config.properties")
public class Application {

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Application.class);

        PersonService personService = context.getBean(PersonServiceImpl.class);
        personService.startTest();

        context.close();
    }
}
