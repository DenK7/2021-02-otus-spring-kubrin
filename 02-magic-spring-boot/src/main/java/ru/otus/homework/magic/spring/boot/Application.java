package ru.otus.homework.magic.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.homework.magic.spring.boot.service.PersonService;
import ru.otus.homework.magic.spring.boot.service.PersonServiceImpl;

import java.io.IOException;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws IOException {
		ApplicationContext context = SpringApplication.run(Application.class, args);
		PersonService personService = context.getBean(PersonServiceImpl.class);
		personService.startTest();
	}

}
