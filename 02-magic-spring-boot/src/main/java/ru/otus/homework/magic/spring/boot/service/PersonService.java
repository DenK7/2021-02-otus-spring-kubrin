package ru.otus.homework.magic.spring.boot.service;

import ru.otus.homework.magic.spring.boot.exception.LoadFileException;
import ru.otus.homework.magic.spring.boot.exception.ParsException;

import java.io.IOException;

public interface PersonService {
    void startTest() throws IOException, LoadFileException, ParsException;
}
