package ru.otus.homework.settings.xml.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
public class PersonTest {

    private final List<Question> questions;

    private final Integer minNumberCorrectAnswers;
    @Setter
    private Integer correctAnswers;
}
