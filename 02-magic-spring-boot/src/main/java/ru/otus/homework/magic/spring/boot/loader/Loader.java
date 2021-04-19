package ru.otus.homework.magic.spring.boot.loader;

import ru.otus.homework.magic.spring.boot.exception.LoadFileException;

import java.io.File;

public interface Loader {
    File loadFile() throws LoadFileException;
}
