package ru.otus.homework.settings.xml.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class Answer {

    private final String isCorrect;
    private final String answerText;
}
