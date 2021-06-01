package ru.otus.mvc.view.service;

public interface AuthorService {

    String getAllAuthors();
    String getAuthorById(String id);
    String getAuthorByName(String authorName);
}
