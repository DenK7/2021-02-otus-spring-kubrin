package ru.otus.jpa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.jpa.domain.Book;
import ru.otus.jpa.domain.Genre;
import ru.otus.jpa.repositories.BookRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе Jpa для работы с Book ")
@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @DisplayName(" возвращать нужное количество строк")
    @Test
    void shouldReturnExpectedBookCount () {
        List<Book> books = bookRepository.findAll();
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
        bookRepository.save(book);
        List<Book> books = bookRepository.findAll();
        assertThat(books.size()).isEqualTo(3);
    }

    @DisplayName(" удалять книгу")
    @Test
    void shouldCorrectDeleteBook() {
        Optional<Book> bookOptional = bookRepository.findById(1L);
        assertThat(bookOptional.isPresent()).isTrue();
        Book book = bookOptional.get();
        bookRepository.delete(book);
        List<Book> books = bookRepository.findAll();
        assertThat(books.size()).isEqualTo(1);
    }

    @DisplayName(" возвращать книгу по id")
    @Test
    void shouldCorrectReturnBookById() {
        Book expectedBook = testEntityManager.find(Book.class, 1L);
        Optional<Book> book = bookRepository.findById(1L);
        assertThat(book.isPresent()).isTrue();
        assertThat(book.get()).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName(" обновлять книгу по id")
    @Test
    void shouldCorrectUpdateBookById() {
        Optional<Book> bookOptionalBefore = bookRepository.findById(1L);
        assertThat(bookOptionalBefore.isPresent()).isTrue();
        Book bookBefore = bookOptionalBefore.get();
        bookBefore.setBookName("TEST");
        bookRepository.save(bookBefore);
        Optional<Book> bookOptional = bookRepository.findById(1L);
        assertThat(bookOptional.isPresent()).isTrue();
        assertThat(bookOptional.get().getBookName()).isEqualTo("TEST");
    }

    @DisplayName(" проверять наличие книг по id")
    @Test
    void shouldCorrectReturnCountBookById() {
        Long cnt = bookRepository.countById(1L);
        assertThat(cnt).isEqualTo(1L);
    }
}
