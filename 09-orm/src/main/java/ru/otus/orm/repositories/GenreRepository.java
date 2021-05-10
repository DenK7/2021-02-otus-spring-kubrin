package ru.otus.orm.repositories;

import ru.otus.orm.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {

    Optional<Genre> findById(Long id);
    List<Genre> findAll();
}
