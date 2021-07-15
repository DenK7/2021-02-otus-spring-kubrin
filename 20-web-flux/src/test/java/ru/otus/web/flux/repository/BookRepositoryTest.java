package ru.otus.web.flux.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import reactor.core.publisher.Flux;
import ru.otus.web.flux.domain.Book;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@EnableConfigurationProperties
@DisplayName("Репозиторий на Mongo для работы с Book должен ")
@ComponentScan("ru.otus.web.flux.configuration")
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @DisplayName(" возвращать нужное количество строк")
    @Test
    void shouldReturnExpectedBookCount () {
        Flux<Book> books = bookRepository.findAll();
        assertThat(books.count().block()).isEqualTo(2L);
    }
}
