package ru.otus.homework.magic.spring.boot;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.CommandNotCurrentlyAvailable;
import org.springframework.shell.Shell;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тест shell ")
@SpringBootTest
public class ShellCommandsTest {

    private static final String COMMAND_LAST_NAME_SHORT = "l";
    private static final String DEFAULT_LAST_NAME = "Petrov";
    private static final String COMMAND_FIRST_NAME_SHORT = "f";
    private static final String DEFAULT_FIRST_NAME = "Petr";

    @Autowired
    private Shell shell;

    @DisplayName(" должен возвращать фамилию по умолчанию")
    @Test
    void shouldReturnLastName() {
        String res = (String) shell.evaluate(() -> COMMAND_LAST_NAME_SHORT);
        assertThat(res).isEqualTo(DEFAULT_LAST_NAME);
    }

    @DisplayName(" должен возвращать имя по умолчанию")
    @Test
    void shouldReturnFirstName() {
        shell.evaluate(() -> COMMAND_LAST_NAME_SHORT);
        String res = (String) shell.evaluate(() -> COMMAND_FIRST_NAME_SHORT);
        assertThat(res).isEqualTo(DEFAULT_FIRST_NAME);
    }

    @DisplayName(" должен возвращать ошибку, так как фамилия не была введена")
    @Test
    void shouldReturnErrorOnEnterFirstName() {
        Object res =  shell.evaluate(() -> COMMAND_FIRST_NAME_SHORT);
        assertThat(res).isInstanceOf(CommandNotCurrentlyAvailable.class);
    }

}
