package ru.otus.orm;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.orm.domain.Genre;
import ru.otus.orm.repositories.GenreRepositoryImpl;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе Jpa для работы с Genre ")
@DataJpaTest
@Import(GenreRepositoryImpl.class)
public class GenreRepositoryTest {

    @Autowired
    private GenreRepositoryImpl genreRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @DisplayName(" возвращать нужное количество строк")
    @Test
    void shouldReturnExpectedGenreCount () {
        List<Genre> genres = genreRepository.findAllGenres();
        assertThat(genres.size()).isEqualTo(2);
    }

    @DisplayName(" возвращать жанр по id")
    @Test
    void shouldCorrectReturnGenreById() {
        Genre expectedGenre = testEntityManager.find(Genre.class, 1L);
        Optional<Genre> genreOptional = genreRepository.findGenreById(1L);
        assertThat(genreOptional.get()).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

}
