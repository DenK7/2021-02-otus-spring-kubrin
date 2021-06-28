package ru.otus.web.flux.dto.convertor;

import ru.otus.web.flux.domain.Genre;
import ru.otus.web.flux.dto.GenreDto;

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
