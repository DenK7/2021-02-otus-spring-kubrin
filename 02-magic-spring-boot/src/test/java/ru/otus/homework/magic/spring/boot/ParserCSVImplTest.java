package ru.otus.homework.magic.spring.boot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.otus.homework.magic.spring.boot.loader.Loader;
import ru.otus.homework.magic.spring.boot.loader.LoaderCSVImpl;
import ru.otus.homework.magic.spring.boot.messages.MessageGenerate;
import ru.otus.homework.magic.spring.boot.parser.Parser;
import ru.otus.homework.magic.spring.boot.parser.ParserCSVImpl;

@DisplayName("class - ParserCSVImplTest")
@SpringBootTest
public class ParserCSVImplTest {

    @ComponentScan({"ru.otus.homework.magic.spring.boot.parser", "ru.otus.homework.magic.spring.boot.loader"})
    @Configuration
    static class ParserCSVImplTestConfig {
    }

    @Autowired
    private Parser parser;
    @Autowired
    private LoaderCSVImpl loader;

    @MockBean
    private MessageGenerate messageGenerate;

    @Test
    @DisplayName("проверка ошибки - если файл не пришел")
    void testErrIfFileIsNull() {
        Assertions.assertThrows(NullPointerException.class, () -> parser.getParsRows(null));
    }

    @Test
    @DisplayName("проверка загрузки файла")
    void testParsFile() {
        Assertions.assertEquals(24l, parser.getParsRows(loader.loadFile()).size());
    }
}
