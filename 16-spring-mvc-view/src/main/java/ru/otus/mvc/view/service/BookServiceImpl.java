package ru.otus.mvc.view.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.mvc.view.domain.Book;
import ru.otus.mvc.view.exception.BookNotFoundException;
import ru.otus.mvc.view.exception.InputNotCorrectException;
import ru.otus.mvc.view.repositories.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Book getBookById(String id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isEmpty()) {
            throw new BookNotFoundException();
        }
        return bookOptional.get();
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
    public Book addBook(Book book) {
        if (book == null) {
            throw new BookNotFoundException();
        }
        if (book.getBookName().trim().isEmpty()) {
            throw new InputNotCorrectException();
        }
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public Book updateBook(Book book) {
        Optional<Book> bookOptional = bookRepository.findById(book.getId());

        if (bookOptional.isEmpty()) {
            throw new BookNotFoundException();
        }
        if (book.getBookName().trim().isEmpty()) {
            throw new InputNotCorrectException();
        }
        bookOptional.get().setBookName(book.getBookName());
        bookOptional.get().setGenre(book.getGenre());
        bookOptional.get().setAuthors(book.getAuthors());
        return bookRepository.save(bookOptional.get());
    }

}
