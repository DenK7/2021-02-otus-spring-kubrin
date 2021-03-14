package ru.otus.homework.settings.xml.cvs;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Component("csvWork")
public class CSVWorkImpl implements CSVWork{
    @Value("${file.name}")
    private File file;

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
