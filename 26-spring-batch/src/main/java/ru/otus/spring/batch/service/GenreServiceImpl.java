package ru.otus.spring.batch.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.batch.domain.h2.H2Genre;
import ru.otus.spring.batch.domain.mongo.MongoGenre;

@Service
public class GenreServiceImpl implements GenreService {

    @Override
    public H2Genre mapGenre(MongoGenre mongoGenre) {
        return H2Genre.builder()
                .genreName(mongoGenre.getGenreName())
                .mongoId(mongoGenre.getId())
                .build();
    }
}
