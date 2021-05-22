package ru.otus.jpa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.jpa.domain.Book;
import ru.otus.jpa.domain.Comment;
import ru.otus.jpa.repositories.CommentRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе Jpa для работы с Comment ")
@DataJpaTest
public class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @DisplayName(" возвращать нужное количество строк")
    @Test
    void shouldReturnExpectedCommentCount () {
        List<Comment> comments = commentRepository.findAll();
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
        commentRepository.save(comment);
        List<Comment> comments = commentRepository.findAll();
        assertThat(comments.size()).isEqualTo(3);
    }

    @DisplayName(" удалять коммент")
    @Test
    void shouldCorrectDeleteComment() {
        Optional<Comment> comment = commentRepository.findById(1L);
        assertThat(comment.isPresent()).isTrue();
        commentRepository.delete(comment.get());
        List<Comment> comments = commentRepository.findAll();
        assertThat(comments.size()).isEqualTo(1);
    }

    @DisplayName(" удалять комменты по id книги")
    @Test
    void shouldCorrectDeleteCommentsByBookId() {
        commentRepository.deleteByBook_Id(1L);
        List<Comment> comments = commentRepository.findAll();
        assertThat(comments.size()).isEqualTo(1);
    }

    @DisplayName(" возвращать коммент по id")
    @Test
    void shouldCorrectReturnCommentById() {
        Comment expectedComment = testEntityManager.find(Comment.class, 1L);
        Optional<Comment> comment = commentRepository.findById(1L);
        assertThat(comment.get()).usingRecursiveComparison().isEqualTo(expectedComment);
    }
    
    @DisplayName(" обновлять коммент по id")
    @Test
    void shouldCorrectUpdateCommentById() {
        Optional<Comment> commentBeforeSave = commentRepository.findById(1L);
        assertThat(commentBeforeSave.isPresent()).isTrue();
        commentBeforeSave.get().setCommentTxt("TEST");
        commentRepository.save(commentBeforeSave.get());
        Optional<Comment> commentAfterSave = commentRepository.findById(1L);
        assertThat(commentAfterSave.get().getCommentTxt()).isEqualTo("TEST");
    }
}
