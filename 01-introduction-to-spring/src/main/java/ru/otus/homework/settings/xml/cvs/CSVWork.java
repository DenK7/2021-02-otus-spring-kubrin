package ru.otus.homework.settings.xml.cvs;

import java.io.IOException;
import java.util.List;

public interface CSVWork {

    List<CVSRow> getCSVRows() throws IOException;
}
