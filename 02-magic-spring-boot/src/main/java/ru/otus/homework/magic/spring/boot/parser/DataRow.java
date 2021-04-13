package ru.otus.homework.magic.spring.boot.parser;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataRow {
    @CsvBindByPosition(position = 0)
    private String typeRow;
    @CsvBindByPosition(position = 1)
    private String nameEn;
    @CsvBindByPosition(position = 2)
    private String nameRu;
}
