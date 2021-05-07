package ru.otus.jdbc.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.jdbc.domain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class BookDAOImpl implements BookDAO {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public BookDAOImpl(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public List<Book> getAllBooks() {
        return namedParameterJdbcOperations.query("select * from book", new BookMapper());
    }

    @Override
    public void addBook(Book book) {
        namedParameterJdbcOperations.update("insert into book (book_name, author_id, genre_id) values (:name, :author, :genre)"
                , Map.of("name", book.getBookName()
                        , "author", book.getAuthorId()
                        , "genre", book.getGenreId()));
    }

    @Override
    public void updateBook(Book book) {
        namedParameterJdbcOperations.update("update book set book_name=:name, author_id=:author, genre_id=:genre where id=:id"
                , Map.of("name", book.getBookName()
                        , "author", book.getAuthorId()
                        , "genre", book.getGenreId()
                        , "id", book.getId()));
    }

    @Override
    public void delBookId(Long id) {
        namedParameterJdbcOperations.update("delete from book where id = :id", Map.of("id", id));
    }

    @Override
    public Book getBookById(Long id) {
        return namedParameterJdbcOperations.queryForObject(
                "select * from book where id = :id", Map.of("id", id), new BookMapper());

    }

    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            Long id = resultSet.getLong("id");
            String bookName = resultSet.getString("book_name");
            Long authorId = resultSet.getLong("author_id");
            Long genreId = resultSet.getLong("genre_id");
            return Book.builder().id(id).bookName(bookName).authorId(authorId).genreId(genreId).build();
        }
    }
}
