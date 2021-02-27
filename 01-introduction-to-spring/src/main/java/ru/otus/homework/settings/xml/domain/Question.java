package ru.otus.homework.settings.xml.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Question {

    private final String questionText;
    private final List<Answer> answers;
}
