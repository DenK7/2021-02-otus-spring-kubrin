package ru.otus.homework.settings.xml.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.homework.settings.xml.cvs.CSVWorkImpl;

import java.io.File;

@Configuration
public class CSVConfig {
    @Value("${file.name}")
    private File file;

    @Bean
    public CSVWorkImpl csvWork() {
        return new CSVWorkImpl(file);
    }
}
