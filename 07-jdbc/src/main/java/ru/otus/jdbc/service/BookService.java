package ru.otus.jdbc.service;

public interface BookService {

    String getAllBooks();
    String getBookById(Long id);
    String deleteBookById(Long id);
    String addBook(String bookName);
    String updBook(Long bookId);
}
