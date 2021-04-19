package ru.otus.homework.magic.spring.boot.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class Person {

    private String lastName;
    private String firstName;
    private PersonTest test;
}
