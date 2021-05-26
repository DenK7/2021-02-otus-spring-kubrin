package ru.otus.mongo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.mongo.domain.Author;
import ru.otus.mongo.domain.Book;
import ru.otus.mongo.domain.Genre;
import ru.otus.mongo.repositories.AuthorRepository;
import ru.otus.mongo.repositories.BookRepository;
import ru.otus.mongo.repositories.GenreRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ru.otus.mongo.service.CommonService.getValue;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, GenreRepository genreRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public String getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return mapBooks(books);
    }

    @Override
    public String getBookByName(String bookName) {
        Optional<Book> book = bookRepository.findByBookName(bookName);
        return book.map(this::mapBook).orElse("Book not found");
    }

    @Override
    @Transactional(readOnly = true)
    public String getBookById(String id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        return bookOptional
                .map(this::mapBook)
                .orElse("Book not found");
    }

    @Override
    @Transactional
    public String deleteBook(String id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isEmpty()) {
            return "Book not found";
        }
        bookRepository.delete(bookOptional.get());
        return "Book deleted";
    }

    @Override
    @Transactional
    public String addBook(String bookName) {
        if (bookName.trim().length() == 0) {
            return "Book is not correct";
        }

        String genreId = getValue("Input genre id");

        Optional<Genre> genreOptional = genreRepository.findById(genreId);
        Book book = genreOptional
                .map(genre ->  Book.builder().bookName(bookName).genre(genre).authors(new ArrayList<>()).build())
                .orElse(null);
        if (book != null) {
            return mapBook(bookRepository.save(book))+". Add authors!!!";
        }
        return "Book not add";
    }

    @Override
    @Transactional
    public String updateBookById(String id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        return bookOptional
                .map(this::changeNameGenreAndUpdateComment)
                .orElse("Book not found");
    }

    @Override
    @Transactional
    public String addAuthorByBookId(String id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        return bookOptional
                .map(this::addAuthorAndUpdateComment)
                .orElse("Book not found");
    }

    @Override
    @Transactional
    public String deleteAuthorByBookId(String id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isEmpty()) {
            return "Book not found";
        }

        String authorId = getValue("Input author id");

        Book book = bookOptional.get();
        for (Author author: book.getAuthors()) {
            if (author.getId().equals(authorId)) {
                book.getAuthors().remove(author);
                break;
            }
        }

        try {
            bookRepository.save(book);
            return "Author delete from book";
        } catch (Exception e) {
            //тут желательно выдавать stack trace
            return "Author is not deleted from book";
        }
    }

    private String addAuthorAndUpdateComment(Book book) {
        String authorId = getValue("Input author id");

        Optional<Author> authorOptional = authorRepository.findById(authorId);
        if (authorOptional.isEmpty()) {
            return "Author id is not correct";
        }

        book.getAuthors().add(authorOptional.get());
        try {
            bookRepository.save(book);
            return "Book updated";
        } catch (Exception e) {
            //тут желательно выдавать stack trace
            return "Book is not updated";
        }
    }

    private String mapBooks(List<Book> books) {
        StringBuilder builder = new StringBuilder();
        for (Book book: books) {
            if (builder.length() > 0) {
                builder.append("; ");
            }
            builder.append(mapBook(book));
        }
        return builder.toString();
    }

    private String mapBook(Book book) {
        StringBuilder builder = new StringBuilder();
        builder.append("id = ").append(book.getId()).append(", book = ").append(book.getBookName())
                .append(", genre id = ").append(book.getGenre().getId()).append(", genre name = ").append(book.getGenre().getGenreName());
        for (Author author: book.getAuthors()) {
            builder.append(", author id = ").append(author.getId()).append(", author name = ").append(author.getAuthorName());
        }
        return builder.toString();
    }

    private String changeNameGenreAndUpdateComment(Book book) {
        String bookName = getValue("Input book name:");
        book.setBookName(bookName);

        String genreId = getValue("Input genre id");
        Optional<Genre> genreOptional = genreRepository.findById(genreId);

        if (genreOptional.isEmpty()) {
            return "Genre id is not correct";
        }
        book.setGenre(genreOptional.get());

        try {
            bookRepository.save(book);
            return "Book updated";
        } catch (Exception e) {
            //тут желательно выдавать stack trace
            return "Book is not updated";
        }
    }
}
