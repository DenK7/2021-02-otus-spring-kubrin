package ru.otus.jdbc.dao;

import ru.otus.jdbc.domain.Book;

import java.util.List;

public interface BookDAO {

    List<Book> getAllBooks();

    Book getBookById(Long id);
    void addBook(Book book);
    void updateBook(Book book);
    void delBookId(Long id);
}
