package ru.otus.homework.settings.xml.cvs;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CVSRow {
    @CsvBindByPosition(position = 0)
    private String typeRow;
    @CsvBindByPosition(position = 1)
    private String dataRow;
}
