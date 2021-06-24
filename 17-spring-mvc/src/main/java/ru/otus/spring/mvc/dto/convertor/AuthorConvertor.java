package ru.otus.spring.mvc.dto.convertor;

import ru.otus.spring.mvc.domain.Author;
import ru.otus.spring.mvc.dto.AuthorDto;

public abstract class AuthorConvertor {

    private AuthorConvertor() {
    }

    public static Author toAuthor(AuthorDto dto) {
        return Author.builder().id(dto.getId()).authorName(dto.getAuthorName()).build();
    }

    public static AuthorDto toAuthorDto(Author author) {
        return AuthorDto.builder().id(author.getId()).authorName(author.getAuthorName()).build();
    }

}
