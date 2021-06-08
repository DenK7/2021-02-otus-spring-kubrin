package ru.otus.mongo.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.otus.mongo.domain.Book;
import ru.otus.mongo.domain.Comment;
import ru.otus.mongo.repositories.BookRepository;
import ru.otus.mongo.repositories.CommentRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@EnableConfigurationProperties
@DisplayName("Репозиторий на Mongo для работы с Comment должен ")
public class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private BookRepository bookRepository;

    @DisplayName(" возвращать нужное количество строк")
    @Test
    void shouldReturnExpectedCommentCount () {
        List<Comment> comments = commentRepository.findAll();
        assertThat(comments.size()).isEqualTo(4);
    }

    @DisplayName(" возвращать коммент по id")
    @Test
    void shouldCorrectReturnCommentById() {
        List<Comment> comments = commentRepository.findAll();
        assertThat(comments.size()).isEqualTo(4);
        Optional<Comment> commentOptional = commentRepository.findById(comments.get(0).getId());
        assertThat(commentOptional.get()).usingRecursiveComparison().isEqualTo(comments.get(0));
    }

    @DisplayName(" добавлять коммент")
    @Test
    void shouldCorrectAddComment() {
        Optional<Book> book = bookRepository.findByBookName("book1");
        Comment comment = Comment.builder()
                .commentTxt("Test")
                .bookId(book.get().getId())
                .build();
        commentRepository.save(comment);
        List<Comment> comments = commentRepository.findByBookId(book.get().getId());
        assertThat(comments.size()).isEqualTo(3);
        commentRepository.delete(comment);
        List<Comment> commentsAfterDelete = commentRepository.findAll();
        assertThat(commentsAfterDelete.size()).isEqualTo(4);
    }

    @DisplayName(" удалять коммент")
    @Test
    void shouldCorrectDeleteComment() {
        Optional<Book> book = bookRepository.findByBookName("book1");
        Comment comment = Comment.builder()
                .commentTxt("Test")
                .bookId(book.get().getId())
                .build();
        commentRepository.save(comment);
        List<Comment> commentsBeforeDelete = commentRepository.findAll();
        assertThat(commentsBeforeDelete.size()).isEqualTo(5);
        commentRepository.delete(comment);
        List<Comment> commentsAfterDelete = commentRepository.findAll();
        assertThat(commentsAfterDelete.size()).isEqualTo(4);
    }

    @DisplayName(" возвращать коммент по id книги")
    @Test
    void shouldCorrectReturnCommentBookId() {
        Optional<Book> book = bookRepository.findByBookName("book1");
        List<Comment> comments = commentRepository.findByBookId(book.get().getId());
        assertThat(comments.size()).usingRecursiveComparison().isEqualTo(2);
    }

    @DisplayName(" обновлять коммент по id")
    @Test
    void shouldCorrectUpdateCommentById() {
        List<Comment> comments = commentRepository.findAll();
        assertThat(comments.size()).isEqualTo(4);
        Comment commentBeforeSave = comments.get(0);
        commentBeforeSave.setCommentTxt("TEST");
        commentRepository.save(commentBeforeSave);
        Optional<Comment> commentAfterSave = commentRepository.findById(commentBeforeSave.getId());
        assertThat(commentAfterSave.get().getCommentTxt()).isEqualTo("TEST");
    }

    @DisplayName(" удалять коммент по id книги")
    @Test
    void shouldCorrectDeleteCommentByBookId() {
        Comment comment = Comment.builder()
                .commentTxt("Test")
                .bookId("test")
                .build();
        commentRepository.save(comment);
        List<Comment> commentsBeforeDelete = commentRepository.findByBookId("test");
        assertThat(commentsBeforeDelete.size()).isEqualTo(1);
        commentRepository.deleteByBookId("test");
        List<Comment> commentsAfterDelete = commentRepository.findByBookId("test");
        assertThat(commentsAfterDelete.size()).isEqualTo(0);
    }
}
