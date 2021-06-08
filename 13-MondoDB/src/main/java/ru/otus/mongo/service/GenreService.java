package ru.otus.mongo.service;

public interface GenreService {
    String getAllGenre();
    String getGenreById(String id);
    String getGenreByName(String genreName);
}
