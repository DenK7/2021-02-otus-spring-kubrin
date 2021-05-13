package ru.otus.orm.repositories;

import ru.otus.orm.domain.Book;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface BookRepository {

    Book saveBook(Book book);
    Optional<Book> findBookById(Long id);
    List<Book> findAllBooks();
    void deleteBookById(Long id);
    BigInteger countBookById(Long id);
}
