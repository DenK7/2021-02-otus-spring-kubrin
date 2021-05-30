package ru.otus.jpa.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.jpa.domain.Genre;
import ru.otus.jpa.repositories.GenreRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService{

    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public String getAllGenre() {
        List<Genre> genres = genreRepository.findAll();
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
        Optional<Genre> genreOptional = genreRepository.findById(id);
        return genreOptional
                .map(genre -> toString())
                .orElse("Genre not found");
    }
}
