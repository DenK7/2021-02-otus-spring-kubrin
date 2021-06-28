package ru.otus.web.flux.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.otus.web.flux.dto.GenreDto;
import ru.otus.web.flux.dto.convertor.GenreConvertor;
import ru.otus.web.flux.repositories.GenreRepository;

@RestController
public class GenreController {

    private final GenreRepository genreRepository;

    public GenreController(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @GetMapping("/genres")
    public Flux<GenreDto> listBooks() {
        return genreRepository.findAll()
                .map(GenreConvertor::toGenreDto);
    }
}

