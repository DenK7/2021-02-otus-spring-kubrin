package ru.otus.spring.mvc.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.mvc.domain.Book;
import ru.otus.spring.mvc.dto.BookDto;
import ru.otus.spring.mvc.exception.BookNotFoundException;
import ru.otus.spring.mvc.exception.InputNotCorrectException;
import ru.otus.spring.mvc.repositories.BookRepository;

import java.util.List;
import java.util.Optional;

import static ru.otus.spring.mvc.dto.convertor.BookConvertor.*;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookDto> getAllBooks() {
        return toListBookDto(bookRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public BookDto getBookById(String id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isEmpty()) {
            throw new BookNotFoundException();
        }
        return toBookDto(bookOptional.get());
    }

    @Override
    @Transactional
    public void deleteBook(String id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isEmpty()) {
            throw new BookNotFoundException();
        }
        bookRepository.delete(bookOptional.get());
    }

    @Override
    @Transactional
    public BookDto saveBook(BookDto book) {
        if (book == null || book.getBookName().trim().isEmpty() || book.getGenre() == null) {
            throw new InputNotCorrectException();
        }

        return toBookDto(bookRepository.save(toBook(book)));
    }
}
