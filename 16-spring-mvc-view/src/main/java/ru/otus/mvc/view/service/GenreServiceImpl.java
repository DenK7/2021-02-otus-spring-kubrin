package ru.otus.mvc.view.service;

import org.springframework.stereotype.Service;
import ru.otus.mvc.view.domain.Genre;
import ru.otus.mvc.view.repositories.GenreRepository;

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
