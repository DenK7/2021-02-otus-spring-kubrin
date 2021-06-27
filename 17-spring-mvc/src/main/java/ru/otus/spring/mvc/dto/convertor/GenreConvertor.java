package ru.otus.spring.mvc.dto.convertor;

import ru.otus.spring.mvc.domain.Genre;
import ru.otus.spring.mvc.dto.GenreDto;

public abstract class GenreConvertor {

    private GenreConvertor() {
    }

    public static Genre toGenre(GenreDto dto) {
        return Genre.builder().id(dto.getId()).genreName(dto.getGenreName()).build();
    }

    public static GenreDto toGenreDto(Genre genre) {
        return GenreDto.builder().id(genre.getId()).genreName(genre.getGenreName()).build();
    }
}
