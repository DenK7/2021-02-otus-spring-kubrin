package ru.otus.homework.settings.xml.cvs;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CSVWorkImpl implements CSVWork{
    private File file;

    public void setFileName(File file) {
        this.file = file;
    }

    public File getFileName() {
        return file;
    }

    @Override
    public List<CVSRow> getCSVRows() throws IOException {
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
