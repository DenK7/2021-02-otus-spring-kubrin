package ru.otus.jdbc.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.otus.jdbc.dao.BookDAO;
import ru.otus.jdbc.domain.Book;
import ru.otus.jdbc.exception.InputNotCorrectException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    private final BookDAO bookDAO;

    public BookServiceImpl(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public String getAllBooks() {
        List<Book> bookList = bookDAO.getAllBooks();
        StringBuilder books = new StringBuilder();
        for (Book book: bookList) {
            if (books.length() > 0 ) {
                books.append("; ");
            }
            books.append(mappBookToString(book));
        }
        return books.toString();
    }

    @Override
    public String getBookById(Long id) {
        try {
            return mappBookToString(bookDAO.getBookById(id));
        } catch (EmptyResultDataAccessException e) {
            return "Book not found";
        }
    }

    @Override
    public String deleteBookById(Long id) {
        bookDAO.delBookId(id);
        return "Book delete";
    }

    @Override
    public String addBook(String bookName) {
        Book book = Book.builder()
                .bookName(bookName)
                .build();
        if (setAuthor(book) && setGenre(book)) {
            bookDAO.addBook(book);
            return "Book added";
        }
        return "Book not added";
    }

    @Override
    public String updBook(Long id) {
        Book book;
        try {
            book = bookDAO.getBookById(id);
        } catch (EmptyResultDataAccessException e) {
            return "Book not found";
        }

        try {
            String bookName = getValue("Input book name:");
            book.setBookName(bookName);
        } catch (InputNotCorrectException e) {
            return "Book name is not correct";
        }
        if (setAuthor(book) && setGenre(book)) {
            bookDAO.updateBook(book);
            return "Book update";
        }
        return "Book not update";
    }

    private String mappBookToString(Book book) {
        return "id = " + book.getId() + " name = " + book.getBookName() +
                " author = " + book.getAuthorName() +
                " genre = " + book.getGenreName();
    }

    //добавить проверку на присутствие в бд
    private boolean setAuthor(Book book){
        try {
            String authorId = getValue("Input author id:");
            book.setAuthorId(Long.valueOf(authorId));
        } catch (Exception e) {
            System.out.println("Author is not correct");
            return false;
        }
        return true;
    }

    //добавить проверку на присутствие в бд
    private boolean setGenre(Book book){
        try {
            String genreId = getValue("Input genre id:");
            book.setGenreId(Long.valueOf(genreId));
        } catch (Exception e) {
            System.out.println("Genre is not correct");
            return false;
        }
        return true;
    }

    private String getValue(String message) throws InputNotCorrectException {
        System.out.println(message);

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        try {
            String value = reader.readLine();
            if (value.trim().length() == 0) {
                throw new InputNotCorrectException();
            }
            return value;
        } catch (IOException e) {
            throw new InputNotCorrectException();
        }
    }
}
