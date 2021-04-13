package ru.otus.homework.magic.spring.boot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.homework.magic.spring.boot.exception.ParsException;
import ru.otus.homework.magic.spring.boot.parser.Parser;

@DisplayName("class - ParserCSVImplTest")
@SpringBootTest
public class ParserCSVImplTest {

    @Autowired
    private Parser parser;

    @Test
    @DisplayName("проверка ошибки - если файл не пришел")
    void testErrIfFileIsNull() throws ParsException {
        Assertions.assertThrows(NullPointerException.class, () -> parser.getParsRows(null));
    }
}
