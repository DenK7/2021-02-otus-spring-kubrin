package ru.otus.homework.magic.spring.boot.parser;

import ru.otus.homework.magic.spring.boot.exception.ParsException;

import java.io.File;
import java.util.List;

public interface Parser {

    List<DataRow> getParsRows(File file) throws ParsException;
}
