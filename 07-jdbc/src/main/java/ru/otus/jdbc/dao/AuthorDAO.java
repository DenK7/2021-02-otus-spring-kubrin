package ru.otus.jdbc.dao;

import ru.otus.jdbc.domain.Author;

import java.util.List;

public interface AuthorDAO {

    void addAuthor(String authorName);
    List<Author> getAllAuthors();
    Author getAuthorById(Long id);
}
