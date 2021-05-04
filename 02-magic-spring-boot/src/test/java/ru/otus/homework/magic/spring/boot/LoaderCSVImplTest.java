package ru.otus.homework.magic.spring.boot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.otus.homework.magic.spring.boot.loader.Loader;
import ru.otus.homework.magic.spring.boot.loader.LoaderCSVImpl;

@DisplayName("class - LoaderCSVImplTest")
@SpringBootTest
public class LoaderCSVImplTest {

    private final String file;

    public LoaderCSVImplTest(@Value("${file.name}") String file) {
        this.file = file;
    }

    @ComponentScan("ru.otus.homework.magic.spring.boot.loader")
    @Configuration
    static class LoaderCSVImplTestConfig {
    }

    @Test
    @DisplayName("проверка на подтягивание файла как ресурса")
    void testFileCSVLoad() {
        // посмотреть что так тоже можно тестить
        Loader loader = new LoaderCSVImpl(file);
        Assertions.assertNotNull(loader.loadFile());
    }
}
