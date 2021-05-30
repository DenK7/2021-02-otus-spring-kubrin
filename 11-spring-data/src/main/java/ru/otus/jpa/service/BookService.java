package ru.otus.jpa.service;

public interface BookService {

    String getAllBooks();
    String getBookById(Long id);
    String addBook(String txt);
    String updateBookById(Long id);
    String deleteBook(Long id);
    String addAuthorByBookId(Long id);
    String deleteAuthorByBookId(Long id);
}
