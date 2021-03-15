package ru.otus.homework.magic.spring.boot.cvs;

import java.io.IOException;
import java.util.List;

public interface CSVWork {

    List<CVSRow> getCSVRows() throws IOException;
}
