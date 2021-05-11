package ru.otus.jdbc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.jdbc.dao.AuthorDAOImpl;
import ru.otus.jdbc.domain.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("DAO для работы с авторами должно ")
@JdbcTest
@Import(AuthorDAOImpl.class)
public class AuthorDAOTest {

    @Autowired
    private AuthorDAOImpl authorDAO;

    @DisplayName(" возвращать нужное количество строк")
    @Test
    void shouldReturnExpectedAuthorCount () {
        List<Author> authors = authorDAO.getAllAuthors();
        assertThat(authors.size()).isEqualTo(2);
    }

    @DisplayName(" добавлять автора")
    @Test
    void shouldCorrectAddAuthor() {
        authorDAO.addAuthor("Test");
        List<Author> authors = authorDAO.getAllAuthors();
        assertThat(authors.size()).isEqualTo(3);
    }

    @DisplayName(" возвращать автора по id")
    @Test
    void shouldCorrectReturnAuthorById() {
        Author expectedAuthor = Author.builder()
                .id(1L)
                .authorName("AUTHOR 1")
                .build();
        Author author = authorDAO.getAuthorById(1L);
        assertThat(author).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName(" возвращать ошибку если автор не найден")
    @Test
    void shouldErrorIfAuthorNotFound() {
        assertThatThrownBy(() -> authorDAO.getAuthorById(0L)).isInstanceOf(EmptyResultDataAccessException.class);
    }
}
