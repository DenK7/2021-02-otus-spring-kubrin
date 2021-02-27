package ru.otus.homework.settings.xml.cvs;

import com.opencsv.exceptions.CsvException;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface CSVWork {

    List<CVSRow> getCSVRows(File file) throws IOException, CsvException;
}
