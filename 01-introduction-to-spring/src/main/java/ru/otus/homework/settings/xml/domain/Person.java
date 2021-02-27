package ru.otus.homework.settings.xml.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Builder
@ToString
@Getter
public class Person {

    private final String lastName;
    private final String firstName;
    private final PersonTest test;
}
