package ru.otus.orm;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.orm.domain.Author;
import ru.otus.orm.repositories.AuthorRepositoryImpl;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе Jpa для работы с Author ")
@DataJpaTest
@Import(AuthorRepositoryImpl.class)
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepositoryImpl authorRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @DisplayName(" возвращать нужное количество строк")
    @Test
    void shouldReturnExpectedAuthorCount () {
        List<Author> authors = authorRepository.findAllAuthors();
        assertThat(authors.size()).isEqualTo(2);
    }

    @DisplayName(" возвращать автора по id")
    @Test
    void shouldCorrectReturnAuthorById() {
        Author expectedAuthor = testEntityManager.find(Author.class, 1L);
        Optional<Author> authorOptional = authorRepository.findAuthorById(1L);
        assertThat(authorOptional.get()).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }
}
