package ru.otus.base.spring.security.service;

import ru.otus.base.spring.security.domain.Book;

import java.util.List;

public interface BookService {

    List<Book> getAllBooks();
    Book getBookById(String id);
    Book addBook(Book book);
    Book updateBook(Book book);
    void deleteBook(String id);
}
