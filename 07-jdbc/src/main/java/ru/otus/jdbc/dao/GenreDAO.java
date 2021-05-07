package ru.otus.jdbc.dao;

import ru.otus.jdbc.domain.Genre;

public interface GenreDAO {

    Genre getGenreById(Long id);
}
