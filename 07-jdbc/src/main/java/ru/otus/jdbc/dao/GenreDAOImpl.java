package ru.otus.jdbc.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.jdbc.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

@Repository
public class GenreDAOImpl implements GenreDAO {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public GenreDAOImpl(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public Genre getGenreById(Long id) {
        return namedParameterJdbcOperations.queryForObject(
                "select * from genre where id = :id", Map.of("id", id), new GenreMapper());
    }

    private static class GenreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            Long id = resultSet.getLong("id");
            String genreName = resultSet.getString("genre_name");
            return Genre.builder().id(id).genreName(genreName).build();
        }
    }
}
