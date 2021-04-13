package ru.otus.homework.magic.spring.boot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.homework.magic.spring.boot.exception.LoadFileException;
import ru.otus.homework.magic.spring.boot.loader.Loader;
import ru.otus.homework.magic.spring.boot.loader.LoaderCSVImpl;
import ru.otus.homework.magic.spring.boot.parser.ParserCSVImpl;

@DisplayName("class - LoaderCSVImplTest")
@SpringBootTest
public class LoaderCSVImplTest {

    private final String file;

    public LoaderCSVImplTest(@Value("${file.name}") String file) {
        this.file = file;
    }

    @Test
    @DisplayName("проверка на подтягивание файла как ресурса")
    void testFileCSVLoad() throws LoadFileException {
        // посмотреть что так тоже можно тестить
        Loader loader = new LoaderCSVImpl(file);
        Assertions.assertNotNull(loader.loadFile());
    }
}
