package ru.otus.spring.mvc.service;

import ru.otus.spring.mvc.dto.AuthorDto;

import java.util.List;

public interface AuthorService {

    List<AuthorDto> getAllAuthors();
}
