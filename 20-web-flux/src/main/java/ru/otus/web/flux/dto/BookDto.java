package ru.otus.web.flux.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class BookDto {

    private String id;

    private String bookName;

    private List<AuthorDto> authors;

    private GenreDto genre;
}
