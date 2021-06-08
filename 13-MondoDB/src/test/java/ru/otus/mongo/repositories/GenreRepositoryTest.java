package ru.otus.mongo.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.otus.mongo.domain.Genre;
import ru.otus.mongo.repositories.GenreRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@EnableConfigurationProperties
@DisplayName("Репозиторий на Mongo для работы с Genre должен ")
public class GenreRepositoryTest {

    @Autowired
    private GenreRepository genreRepository;

    @DisplayName(" возвращать нужное количество строк")
    @Test
    void shouldReturnExpectedGenreCount () {
        List<Genre> genres = genreRepository.findAll();
        assertThat(genres.size()).isEqualTo(2);
    }

    @DisplayName(" возвращать жанр по id")
    @Test
    void shouldCorrectReturnGenreById() {
        List<Genre> genres = genreRepository.findAll();
        assertThat(genres.size()).isEqualTo(2);
        Optional<Genre> genreOptional = genreRepository.findById(genres.get(0).getId());
        assertThat(genreOptional.get()).usingRecursiveComparison().isEqualTo(genres.get(0));
    }

    @DisplayName(" возвращать жанр по наименованию")
    @Test
    void shouldCorrectReturnGenreByName() {
        List<Genre> genres = genreRepository.findAll();
        assertThat(genres.size()).isEqualTo(2);
        Genre genre = genreRepository.findGenreByGenreName(genres.get(0).getGenreName());
        assertThat(genre).usingRecursiveComparison().isEqualTo(genres.get(0));
    }
}
