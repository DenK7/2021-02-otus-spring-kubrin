package ru.otus.orm.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.orm.domain.Genre;
import ru.otus.orm.repositories.GenreRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService{

    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    @Transactional
    public String getAllGenre() {
        List<Genre> genres = genreRepository.findAllGenres();
        StringBuilder builder = new StringBuilder();
        for (Genre genre: genres) {
            if (builder.length() > 0) {
                builder.append("; ");
            }
            builder.append(genre.toString());
        }
        return builder.toString();
    }

    @Override
    public String getGenreById(Long id) {
        Optional<Genre> genreOptional = genreRepository.findGenreById(id);
        return genreOptional
                .map(genre -> toString())
                .orElse("Genre not found");
    }
}
