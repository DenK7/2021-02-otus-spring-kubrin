package ru.otus.orm.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.orm.domain.Author;
import ru.otus.orm.domain.Book;
import ru.otus.orm.domain.Genre;
import ru.otus.orm.repositories.AuthorRepository;
import ru.otus.orm.repositories.BookRepository;
import ru.otus.orm.repositories.CommentRepository;
import ru.otus.orm.repositories.GenreRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ru.otus.orm.service.CommonService.getValue;

@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;
    private final CommentRepository commentRepository;

    public BookServiceImpl(BookRepository bookRepository, GenreRepository genreRepository, AuthorRepository authorRepository, CommentRepository commentRepository) {
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.authorRepository = authorRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    @Transactional
    public String getAllBooks() {
        List<Book> books = bookRepository.findAllBooks();
        StringBuilder builder = new StringBuilder();
        for (Book book: books) {
            if (builder.length() > 0) {
                builder.append("; ");
            }
            builder.append(mapBook(book));
        }
        return builder.toString();
    }

    @Override
    @Transactional
    public String getBookById(Long id) {
        Optional<Book> bookOptional = bookRepository.findBookById(id);
        return bookOptional
                .map(this::mapBook)
                .orElse("Book not found");
    }

    @Override
    @Transactional
    public String deleteBookById(Long id) {
        //двунаправленная связь, нужно удалять в обоих направлениях
        commentRepository.deleteCommentsByBookId(id);
        bookRepository.deleteBookById(id);
        return "Book deleted";
    }

    @Override
    @Transactional
    public String addBook(String bookName) {
        if (bookName.trim().length() == 0) {
            return "Book is not correct";
        }

        String genreIdVal = getValue("Input genre id");
        long genreId;
        try {
            genreId = Long.parseLong(genreIdVal);
        } catch (Exception e) {
            //тут желательно выдавать stack trace
            return "Genre id is not correct";
        }

        Optional<Genre> genreOptional = genreRepository.findGenreById(genreId);
        Book book = genreOptional
                .map(genre ->  Book.builder().bookName(bookName).genre(genre).authors(new ArrayList<>()).comments(new ArrayList<>()).build())
                .orElse(null);
        if (book != null) {
            return mapBook(bookRepository.saveBook(book))+". Add authors!!!";
        }
        return "Book not add";
    }

    @Override
    @Transactional
    public String updateBookById(Long id) {
        Optional<Book> bookOptional = bookRepository.findBookById(id);
        return bookOptional
                .map(this::changeNameGenreAndUpdateComment)
                .orElse("Book not found");
    }

    @Override
    @Transactional
    public String addAuthorByBookId(Long id) {
        Optional<Book> bookOptional = bookRepository.findBookById(id);
        return bookOptional
                .map(this::addAuthorAndUpdateComment)
                .orElse("Book not found");
    }

    @Override
    @Transactional
    public String deleteAuthorByBookId(Long id) {
        Optional<Book> bookOptional = bookRepository.findBookById(id);
        if (bookOptional.isEmpty()) {
            return "Book not found";
        }

        String authorIdVal = getValue("Input author id");
        Long authorId;
        try {
            authorId = Long.valueOf(authorIdVal);
        } catch (Exception e) {
            //тут желательно выдавать stack trace
            return "Author id is not correct";
        }

        Book book = bookOptional.get();
        for (Author author: book.getAuthors()) {
            if (author.getId().equals(authorId)) {
                book.getAuthors().remove(author);
                break;
            }
        }

        try {
            bookRepository.saveBook(book);
            return "Author delete from book";
        } catch (Exception e) {
            //тут желательно выдавать stack trace
            return "Author is not deleted from book";
        }
    }

    private String addAuthorAndUpdateComment(Book book) {
        String authorIdVal = getValue("Input author id");
        long authorId;
        try {
            authorId = Long.parseLong(authorIdVal);
        } catch (Exception e) {
            //тут желательно выдавать stack trace
            return "Author id is not correct";
        }

        Optional<Author> authorOptional = authorRepository.findAuthorById(authorId);
        if (authorOptional.isEmpty()) {
            return "Author id is not correct";
        }

        book.getAuthors().add(authorOptional.get());
        try {
            bookRepository.saveBook(book);
            return "Book updated";
        } catch (Exception e) {
            //тут желательно выдавать stack trace
            return "Book is not updated";
        }
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

        String genreIdVal = getValue("Input genre id");
        long genreId;
        try {
            genreId = Long.parseLong(genreIdVal);
        } catch (Exception e) {
            return "Genre id is not correct";
        }
        Optional<Genre> genreOptional = genreRepository.findGenreById(genreId);

        if (genreOptional.isEmpty()) {
            return "Genre id is not correct";
        }
        book.setGenre(genreOptional.get());

        try {
            bookRepository.saveBook(book);
            return "Book updated";
        } catch (Exception e) {
            //тут желательно выдавать stack trace
            return "Book is not updated";
        }
    }
}
