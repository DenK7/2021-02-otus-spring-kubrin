package ru.otus.orm;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.orm.domain.Book;
import ru.otus.orm.domain.Genre;
import ru.otus.orm.repositories.BookRepositoryImpl;
import ru.otus.orm.repositories.CommentRepositoryImpl;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе Jpa для работы с Book ")
@DataJpaTest
@Import({BookRepositoryImpl.class, CommentRepositoryImpl.class})
public class BookRepositoryTest {

    @Autowired
    private BookRepositoryImpl bookRepository;
    @Autowired
    private CommentRepositoryImpl commentRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @DisplayName(" возвращать нужное количество строк")
    @Test
    void shouldReturnExpectedBookCount () {
        List<Book> books = bookRepository.findAllBooks();
        assertThat(books.size()).isEqualTo(2);
    }

    @DisplayName(" добавлять книгу")
    @Test
    void shouldCorrectAddBook() {
        Genre genre = Genre.builder().id(1L).build();
        Book book = Book.builder()
                .bookName("Test")
                .genre(genre)
                .build();
        bookRepository.saveBook(book);
        List<Book> books = bookRepository.findAllBooks();
        assertThat(books.size()).isEqualTo(3);
    }

    @DisplayName(" удалять книгу")
    @Test
    void shouldCorrectDeleteBook() {
        Optional<Book> bookOptional = bookRepository.findBookById(1L);
        assertThat(bookOptional.isPresent()).isTrue();
        Book book = bookOptional.get();
        bookRepository.deleteBook(book);
        List<Book> books = bookRepository.findAllBooks();
        assertThat(books.size()).isEqualTo(1);
    }

    @DisplayName(" возвращать книгу по id")
    @Test
    void shouldCorrectReturnBookById() {
        Book expectedBook = testEntityManager.find(Book.class, 1L);
        Optional<Book> book = bookRepository.findBookById(1L);
        assertThat(book.isPresent()).isTrue();
        assertThat(book.get()).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName(" обновлять книгу по id")
    @Test
    void shouldCorrectUpdateBookById() {
        Optional<Book> bookOptionalBefore = bookRepository.findBookById(1L);
        assertThat(bookOptionalBefore.isPresent()).isTrue();
        Book bookBefore = bookOptionalBefore.get();
        bookBefore.setBookName("TEST");
        bookRepository.saveBook(bookBefore);
        Optional<Book> bookOptional = bookRepository.findBookById(1L);
        assertThat(bookOptional.isPresent()).isTrue();
        assertThat(bookOptional.get().getBookName()).isEqualTo("TEST");
    }

    @DisplayName(" проверять наличие книг по id")
    @Test
    void shouldCorrectReturnCountBookById() {
        BigInteger cnt = bookRepository.countBookById(1L);
        assertThat(cnt).isEqualTo(1L);
    }
}
