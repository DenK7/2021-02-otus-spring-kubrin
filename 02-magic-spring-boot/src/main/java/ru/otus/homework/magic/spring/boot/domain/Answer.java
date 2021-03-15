package ru.otus.homework.magic.spring.boot.domain;

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
