package ru.otus.spring.mvc.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.mvc.dto.GenreDto;
import ru.otus.spring.mvc.dto.convertor.GenreConvertor;
import ru.otus.spring.mvc.repositories.GenreRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<GenreDto> getAllGenre() {
        return genreRepository.findAll().stream().map(GenreConvertor::toGenreDto).collect(Collectors.toList());
    }
}
