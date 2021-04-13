package ru.otus.homework.magic.spring.boot.loader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.homework.magic.spring.boot.exception.LoadFileException;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

@Component
public class LoaderCSVImpl implements Loader {

    private final String file;

    public LoaderCSVImpl(@Value("${file.name}") String file) {
        this.file = file;
    }

    @Override
    public File loadFile() throws LoadFileException {
        URL url = getClass().getClassLoader().getResource(file);
        try {
            return Paths.get(url.toURI()).toFile();
        } catch (URISyntaxException e) {
            throw new LoadFileException();
        }
    }
}
