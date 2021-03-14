package ru.otus.homework.settings.xml.domain;

import lombok.*;

@ToString
@Getter
@Setter
public class Person {

    private String lastName;
    private String firstName;
    private PersonTest test;
}
