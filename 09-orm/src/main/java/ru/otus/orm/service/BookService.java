package ru.otus.orm.service;

public interface BookService {

    String getAllBooks();
    String getBookById(Long id);
    String addBook(String txt);
    String updateBookById(Long id);
    String deleteBookById(Long id);
    String addAuthorByBookId(Long id);
    String deleteAuthorByBookId(Long id);
}
