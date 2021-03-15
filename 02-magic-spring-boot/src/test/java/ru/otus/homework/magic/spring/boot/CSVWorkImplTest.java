package ru.otus.homework.magic.spring.boot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.homework.magic.spring.boot.cvs.CSVWorkImpl;

@DisplayName("class - CSVWorkImplTest")
@SpringBootTest
public class CSVWorkImplTest {

    @Autowired
    private CSVWorkImpl csvWorkImpl;

//    @Test
//    @DisplayName("должен выдавать ошибку если файл не приходит")
//    void testErrLoad () {
//        CSVWorkImpl csvWorkImpl = new CSVWorkImpl();
//        Assertions.assertThrows(NullPointerException.class, () -> csvWorkImpl.getCSVRows());
//    }
}
