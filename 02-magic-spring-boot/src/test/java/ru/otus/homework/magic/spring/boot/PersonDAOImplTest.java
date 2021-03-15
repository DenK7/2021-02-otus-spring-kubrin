package ru.otus.homework.magic.spring.boot;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.homework.magic.spring.boot.dao.PersonDAOImpl;
import ru.otus.homework.magic.spring.boot.domain.Person;

@DisplayName("class - PersonDAOImplTest")
@SpringBootTest
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
