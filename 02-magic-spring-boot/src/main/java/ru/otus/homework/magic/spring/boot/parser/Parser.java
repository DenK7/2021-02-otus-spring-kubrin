package ru.otus.homework.magic.spring.boot.parser;

import java.io.File;
import java.util.List;

public interface Parser {

    List<DataRow> getParsRows(File file) ;
}
