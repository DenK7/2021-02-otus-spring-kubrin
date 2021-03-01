package ru.otus.homework.settings.xml.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.homework.settings.xml.cvs.CSVWorkImpl;

@DisplayName("class - CSVWorkImplTest")
public class CSVWorkImplTest {

    @Test
    @DisplayName("должен выдавать ошибку если файл не приходит")
    void testErrLoad () {
        CSVWorkImpl csvWorkImpl = new CSVWorkImpl();
        Assertions.assertThrows(NullPointerException.class, () -> csvWorkImpl.getCSVRows());
    }
}
