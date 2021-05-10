package ru.otus.jdbc.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Book {
    private Long id;
    private String bookName;
    private Long authorId;
    private String authorName;
    private Long genreId;
    private String genreName;
}
