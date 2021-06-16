package ru.otus.base.spring.security.service;

import ru.otus.base.spring.security.domain.Author;

import java.util.List;

public interface AuthorService {

    List<Author> getAllAuthors();
}
