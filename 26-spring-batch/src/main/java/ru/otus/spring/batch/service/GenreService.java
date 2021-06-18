package ru.otus.spring.batch.service;

import ru.otus.spring.batch.domain.h2.H2Genre;
import ru.otus.spring.batch.domain.mongo.MongoGenre;

public interface GenreService {

    H2Genre mapGenre(MongoGenre mongoGenre);
    String getAllGenre();

}
