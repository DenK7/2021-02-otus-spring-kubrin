package ru.otus.homework.magic.spring.boot.parser;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Component;
import ru.otus.homework.magic.spring.boot.exception.ParsException;
import ru.otus.homework.magic.spring.boot.messages.MessageGenerate;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Component
public class ParserCSVImpl implements Parser {

    private final MessageGenerate messageGenerate;

    public ParserCSVImpl(MessageGenerate messageGenerate) {
        this.messageGenerate = messageGenerate;
    }

    @Override
    public List<DataRow> getParsRows(File file) {
        if (file == null) {
            throw new NullPointerException(messageGenerate.getMessage("FILE.NOT.FOUND"));
        }

        try {
            return  new CsvToBeanBuilder(new FileReader(file))
                    .withType(DataRow.class)
                    .withSeparator(';')
                    .build()
                    .parse();
        } catch (IOException e ) {
            throw new ParsException();
        }
    }
}
