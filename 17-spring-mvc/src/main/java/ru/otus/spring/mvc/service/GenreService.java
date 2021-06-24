package ru.otus.spring.mvc.service;

import ru.otus.spring.mvc.dto.GenreDto;

import java.util.List;

public interface GenreService {
    List<GenreDto> getAllGenre();
}
