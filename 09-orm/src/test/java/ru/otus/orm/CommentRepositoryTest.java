package ru.otus.orm;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.orm.domain.Book;
import ru.otus.orm.domain.Comment;
import ru.otus.orm.repositories.CommentRepositoryImpl;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе Jpa для работы с Comment ")
@DataJpaTest
@Import(CommentRepositoryImpl.class)
public class CommentRepositoryTest {

    @Autowired
    private CommentRepositoryImpl commentRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @DisplayName(" возвращать нужное количество строк")
    @Test
    void shouldReturnExpectedCommentCount () {
        List<Comment> comments = commentRepository.findAllComments();
        assertThat(comments.size()).isEqualTo(2);
    }

    @DisplayName(" добавлять коммент")
    @Test
    void shouldCorrectAddComment() {
        Book book = Book.builder().id(1L).build();
        Comment comment = Comment.builder()
                .commentTxt("Test")
                .book(book)
                .build();
        commentRepository.saveComment(comment);
        List<Comment> comments = commentRepository.findAllComments();
        assertThat(comments.size()).isEqualTo(3);
    }

    @DisplayName(" удалять коммент")
    @Test
    void shouldCorrectDeleteComment() {
        Optional<Comment> comment = commentRepository.findCommentById(1L);
        assertThat(comment.isPresent()).isTrue();
        commentRepository.deleteComment(comment.get());
        List<Comment> comments = commentRepository.findAllComments();
        assertThat(comments.size()).isEqualTo(1);
    }

    @DisplayName(" удалять комменты по id книги")
    @Test
    void shouldCorrectDeleteCommentsByBookId() {
        commentRepository.deleteCommentsByBookId(1L);
        List<Comment> comments = commentRepository.findAllComments();
        assertThat(comments.size()).isEqualTo(1);
    }

    @DisplayName(" возвращать коммент по id")
    @Test
    void shouldCorrectReturnCommentById() {
        Comment expectedComment = testEntityManager.find(Comment.class, 1L);
        Optional<Comment> comment = commentRepository.findCommentById(1L);
        assertThat(comment.get()).usingRecursiveComparison().isEqualTo(expectedComment);
    }
    
    @DisplayName(" обновлять коммент по id")
    @Test
    void shouldCorrectUpdateCommentById() {
        commentRepository.updateCommentById(1L, "TEST");
        Optional<Comment> commentOptional = commentRepository.findCommentById(1L);
        assertThat(commentOptional.get().getCommentTxt()).isEqualTo("TEST");
    }
}
