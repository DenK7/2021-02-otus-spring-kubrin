package ru.otus.homework.settings.xml.cvs;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CSVWorkImpl implements CSVWork{
    private String fileName;

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    @Override
    public List<CVSRow> getCSVRows(File file) throws IOException {
        if (file == null) {
            throw new NullPointerException("File is null");
        }
        List<CVSRow> cvsRows = new CsvToBeanBuilder(new FileReader(file))
                .withType(CVSRow.class)
                .withSeparator(';')
                .build()
                .parse();

        return cvsRows;
    }
}
