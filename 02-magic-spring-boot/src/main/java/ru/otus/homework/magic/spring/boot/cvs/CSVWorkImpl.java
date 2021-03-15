package ru.otus.homework.magic.spring.boot.cvs;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

@Component("csvWork")
public class CSVWorkImpl implements CSVWork{

    private File file;
    private MessageSource messageSource;

    public CSVWorkImpl(
            @Value("${file.name}") File file, MessageSource messageSource) {
        this.file = file;
        this.messageSource = messageSource;
    }

    @Override
    public List<CVSRow> getCSVRows() throws IOException {
        if (file == null) {
            throw new NullPointerException(messageSource.getMessage("FILE.NOT.FOUND", null, Locale.getDefault()));
        }
        List<CVSRow> cvsRows = new CsvToBeanBuilder(new FileReader(file))
                .withType(CVSRow.class)
                .withSeparator(';')
                .build()
                .parse();

        return cvsRows;
    }
}
