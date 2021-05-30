package ru.otus.mongo.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.otus.mongo.domain.Book;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@EnableConfigurationProperties
@DisplayName("Репозиторий на Mongo для работы с Book должен ")
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @DisplayName(" возвращать нужное количество строк")
    @Test
    void shouldReturnExpectedBookCount () {
        List<Book> books = bookRepository.findAll();
        assertThat(books.size()).isEqualTo(2);
    }

    @DisplayName(" возвращать книгу по имени")
    @Test
    void shouldCorrectReturnBookByName() {
        Optional<Book> book = bookRepository.findByBookName("book1");
        assertThat(book.isPresent()).isTrue();
    }

    @DisplayName(" возвращать книгу по id")
    @Test
    void shouldCorrectReturnBookById() {
        List<Book> books = bookRepository.findAll();
        assertThat(books.size()).isEqualTo(2);
        Optional<Book> bookOptional = bookRepository.findById(books.get(0).getId());
        assertThat(bookOptional.get()).usingRecursiveComparison().isEqualTo(books.get(0));
    }

    @DisplayName(" добавлять и удалять книгу")
    @Test
    void shouldCorrectAddAndDeleteBook() {
        Book book = Book.builder()
                .bookName("test")
                .build();
        bookRepository.save(book);
        Optional<Book> bookAfterSave = bookRepository.findByBookName("test");
        assertThat(bookAfterSave.isPresent()).isTrue();
        bookRepository.delete(bookAfterSave.get());
        Optional<Book> bookAfterDelete = bookRepository.findByBookName("test");
        assertThat(bookAfterDelete.isEmpty()).isTrue();
    }

    @DisplayName(" изменять книгу")
    @Test
    void shouldCorrectUpdateBook() {
        Optional<Book> book = bookRepository.findByBookName("book2");
        assertThat(book.isPresent()).isTrue();
        book.get().setBookName("book2_updated");
        bookRepository.save(book.get());
        Optional<Book> bookAfterUpdate = bookRepository.findByBookName("book2_updated");
        assertThat(bookAfterUpdate.isPresent()).isTrue();
    }

}
