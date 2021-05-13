package ru.otus.orm.repositories;

import ru.otus.orm.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {

    Optional<Author> findAuthorById(Long id);
    List<Author> findAllAuthors();
}
