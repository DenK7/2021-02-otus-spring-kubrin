package ru.otus.spring.batch.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.batch.domain.h2.H2Author;
import ru.otus.spring.batch.domain.h2.H2Book;
import ru.otus.spring.batch.domain.h2.H2Genre;
import ru.otus.spring.batch.domain.mongo.MongoAuthor;
import ru.otus.spring.batch.domain.mongo.MongoBook;
import ru.otus.spring.batch.repositories.h2.H2AuthorRepository;
import ru.otus.spring.batch.repositories.h2.H2BookRepository;
import ru.otus.spring.batch.repositories.h2.H2GenreRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final H2GenreRepository genreRepository;
    private final H2AuthorRepository authorRepository;
    private final H2BookRepository bookRepository;

    public BookServiceImpl(H2GenreRepository genreRepository, H2AuthorRepository authorRepository
            , H2BookRepository bookRepository) {
        this.genreRepository = genreRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }


    @Override
    public H2Book mapBook(MongoBook mongoBook) {
        H2Book book = H2Book.builder()
                .bookName(mongoBook.getBookName())
                .h2Authors(new ArrayList<>())
                .build();

        if (mongoBook.getMongoGenre() != null) {
            Optional<H2Genre> genre = genreRepository.findByMongoId(mongoBook.getMongoGenre().getId());
            genre.ifPresent(book::setH2Genre);
        }

        for (MongoAuthor mongoAuthor: mongoBook.getMongoAuthors()) {
            Optional<H2Author> author = authorRepository.findByMongoId(mongoAuthor.getId());
            author.ifPresent(book.getH2Authors()::add);
        }

        return book;
    }

    @Override
    @Transactional(readOnly = true)
    public String getAllBooks() {
        List<H2Book> books = bookRepository.findAll();
        StringBuilder builder = new StringBuilder();
        for (H2Book book: books) {
            if (builder.length() > 0) {
                builder.append("; ");
            }
            builder.append(getStringBook(book));
        }
        return builder.toString();
    }

    private String getStringBook(H2Book book) {
        StringBuilder builder = new StringBuilder();
        builder.append("id = ").append(book.getId()).append(", book = ").append(book.getBookName())
                .append(", genre id = ").append(book.getH2Genre().getId()).append(", genre name = ").append(book.getH2Genre().getGenreName());
        for (H2Author author: book.getH2Authors()) {
            builder.append(", author id = ").append(author.getId()).append(", author name = ").append(author.getAuthorName());
        }
        return builder.toString();
    }
}
