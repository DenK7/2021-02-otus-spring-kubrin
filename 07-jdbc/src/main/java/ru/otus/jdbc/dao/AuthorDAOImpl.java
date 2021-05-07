package ru.otus.jdbc.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.jdbc.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class AuthorDAOImpl implements AuthorDAO {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public AuthorDAOImpl(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public void addAuthor(String authorName) {
        namedParameterJdbcOperations.update("insert into author (author_name) values (:name)",
                Map.of("name", authorName));
    }

    @Override
    public List<Author> getAllAuthors() {
        return namedParameterJdbcOperations.query("select * from author", new AuthorMapper());
    }

    @Override
    public Author getAuthorById(Long id) {
        return namedParameterJdbcOperations.queryForObject(
                "select * from author where id = :id", Map.of("id", id), new AuthorMapper());
    }

    private static class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            Long id = resultSet.getLong("id");
            String authorName = resultSet.getString("author_name");
            return Author.builder().id(id).authorName(authorName).build();
        }
    }
}
