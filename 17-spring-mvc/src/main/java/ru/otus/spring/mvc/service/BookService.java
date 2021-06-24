package ru.otus.spring.mvc.service;

import ru.otus.spring.mvc.dto.BookDto;

import java.util.List;

public interface BookService {

    List<BookDto> getAllBooks();
    BookDto getBookById(String id);
    BookDto saveBook(BookDto book);
    void deleteBook(String id);
}
