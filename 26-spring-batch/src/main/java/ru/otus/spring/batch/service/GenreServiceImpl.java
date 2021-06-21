package ru.otus.spring.batch.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.batch.domain.h2.H2Genre;
import ru.otus.spring.batch.domain.mongo.MongoGenre;
import ru.otus.spring.batch.repositories.h2.H2GenreRepository;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final H2GenreRepository genreRepository;

    public GenreServiceImpl(H2GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public H2Genre mapGenre(MongoGenre mongoGenre) {
        return H2Genre.builder()
                .genreName(mongoGenre.getGenreName())
                .mongoId(mongoGenre.getId())
                .build();
    }

    @Override
    public String getAllGenre() {
        List<H2Genre> genres = genreRepository.findAll();
        StringBuilder builder = new StringBuilder();
        for (H2Genre genre: genres) {
            if (builder.length() > 0) {
                builder.append("; ");
            }
            builder.append(genre.toString());
        }
        return builder.toString();
    }
}
