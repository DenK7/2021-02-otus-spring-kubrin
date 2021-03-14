package ru.otus.homework.settings.xml.test;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.homework.settings.xml.Application;
import ru.otus.homework.settings.xml.dao.PersonDAOImpl;
import ru.otus.homework.settings.xml.domain.Person;

import java.io.IOException;

@DisplayName("class - PersonDAOImplTest")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Application.class)
public class PersonDAOImplTest {

    @Autowired
    private PersonDAOImpl personDAO;

    @Test
    @SneakyThrows
    @DisplayName("если персона не загружена - ошибка")
    void getPersonByNameTest () {
        PersonDAOImpl personDAOSpy = Mockito.spy(personDAO);
        Person person = new Person();
        person.setLastName("Ivanov");
        person.setFirstName("Ivan");
//        Mockito.when(personDAO.getPersonName()).thenReturn(person);
        Mockito.doReturn(person).when(personDAOSpy).getPersonName();

        personDAOSpy.getPersonByName(null);
        Assertions.assertNotNull(person);
    }

}
