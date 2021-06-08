package ru.otus.mvc.view.service;

import ru.otus.mvc.view.domain.Book;

import java.util.List;

public interface BookService {

    List<Book> getAllBooks();
    Book getBookById(String id);
    Book addBook(Book book);
    Book updateBook(Book book);
    void deleteBook(String id);
}
