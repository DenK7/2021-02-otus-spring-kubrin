package ru.otus.mongo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.otus.mongo.domain.Author;
import ru.otus.mongo.repositories.AuthorRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@EnableConfigurationProperties
@DisplayName("Репозиторий на Mongo для работы с Author ")
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @DisplayName(" возвращать нужное количество строк")
    @Test
    void shouldReturnExpectedAuthorCount () {
        List<Author> authors = authorRepository.findAll();
        assertThat(authors.size()).isEqualTo(3);
    }

    @DisplayName(" возвращать автора по id")
    @Test
    void shouldCorrectReturnAuthorById() {
        List<Author> authors = authorRepository.findAll();
        assertThat(authors.size()).isEqualTo(3);
        Optional<Author> authorOptional = authorRepository.findById(authors.get(0).getId());
        assertThat(authorOptional.get()).usingRecursiveComparison().isEqualTo(authors.get(0));
    }
}
