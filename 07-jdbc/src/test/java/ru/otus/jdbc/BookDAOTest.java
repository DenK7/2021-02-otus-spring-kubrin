package ru.otus.jdbc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.jdbc.dao.BookDAOImpl;
import ru.otus.jdbc.domain.Book;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Dao для работы с книгами должно ")
@JdbcTest
@Import(BookDAOImpl.class)
public class BookDAOTest {

    @Autowired
    private BookDAOImpl bookDAO;

    @DisplayName(" возвращать нужное количество строк")
    @Test
    void shouldReturnExpectedBookCount () {
        List<Book> books = bookDAO.getAllBooks();
        assertThat(books.size()).isEqualTo(1);
    }

//    @Rollback(value = false)
    @DisplayName(" добавлять книгу")
    @Test
    void shouldCorrectAddBook() {
        Book book = Book.builder()
                .bookName("Test")
                .authorId(1L)
                .genreId(1L)
                .build();
        bookDAO.addBook(book);
        List<Book> books = bookDAO.getAllBooks();
        assertThat(books.size()).isEqualTo(2);
    }

    @DisplayName(" удалять книгу")
    @Test
    void shouldCorrectDeleteBook() {
        bookDAO.delBookId(1L);
        assertThatThrownBy(() -> bookDAO.getBookById(1L)).isInstanceOf(EmptyResultDataAccessException.class);
    }

    @DisplayName(" возвращать книгу по id")
    @Test
    void shouldCorrectReturnBookById() {
        Book expectedBook = Book.builder()
                .id(1L)
                .bookName("BOOK 1")
                .authorId(1L)
                .authorName("AUTHOR 1")
                .genreId(1L)
                .genreName("GENRE 1")
                .build();
        Book book = bookDAO.getBookById(1L);
        assertThat(book).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName(" возвращать ошибку если книга не найдена")
    @Test
    void shouldErrorIfBookNotFound() {
        assertThatThrownBy(() -> bookDAO.getBookById(0L)).isInstanceOf(EmptyResultDataAccessException.class);
    }

    @DisplayName(" возвращать ошибку если id автора нет в бд ")
    @Test
    void shouldErrorIfAuthorIdIsNotFound() {
        Book book = Book.builder()
                .bookName("Test")
                .authorId(0L)
                .genreId(1L)
                .build();
        assertThatThrownBy(() -> bookDAO.addBook(book)).isInstanceOf(DataIntegrityViolationException.class);
    }
}
