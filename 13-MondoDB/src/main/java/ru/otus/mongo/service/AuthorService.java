package ru.otus.mongo.service;

public interface AuthorService {

    String getAllAuthors();
    String getAuthorById(String id);
    String getAuthorByName(String authorName);
}
