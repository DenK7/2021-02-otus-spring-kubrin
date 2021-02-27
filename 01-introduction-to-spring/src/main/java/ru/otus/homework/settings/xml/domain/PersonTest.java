package ru.otus.homework.settings.xml.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class PersonTest {

    private final List<Question> questions;

    //todo add results
}
