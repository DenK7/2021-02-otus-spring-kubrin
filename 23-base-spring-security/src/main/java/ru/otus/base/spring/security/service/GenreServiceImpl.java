package ru.otus.base.spring.security.service;

import org.springframework.stereotype.Service;
import ru.otus.base.spring.security.domain.Genre;
import ru.otus.base.spring.security.repositories.GenreRepository;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<Genre> getAllGenre() {
        return genreRepository.findAll();
    }
}
