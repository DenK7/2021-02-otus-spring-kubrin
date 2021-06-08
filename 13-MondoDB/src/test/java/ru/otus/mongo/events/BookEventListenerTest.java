package ru.otus.mongo.events;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.mongo.domain.Book;
import ru.otus.mongo.domain.Comment;
import ru.otus.mongo.repositories.BookRepository;
import ru.otus.mongo.repositories.CommentRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@EnableConfigurationProperties
@ComponentScan("ru.otus.mongo.events")
@DisplayName("EventListener на Mongo для работы с Book должен ")
public class BookEventListenerTest {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CommentRepository commentRepository;

    @DisplayName(" удалять все комментарии при удаленнии книги")
    @Test
    void shouldCorrectAddAndDeleteBook() {
        Book book = Book.builder()
                .bookName("test")
                .build();
        bookRepository.save(book);
        Optional<Book> bookAfterSave = bookRepository.findByBookName("test");
        assertThat(bookAfterSave.isPresent()).isTrue();
        String bookId = bookAfterSave.get().getId();
        Comment comment = Comment.builder()
                .commentTxt("test_comment")
                .bookId(bookId)
                .build();
        commentRepository.save(comment);
        List<Comment> commentsAfterSave = commentRepository.findByBookId(bookId);
        assertThat(commentsAfterSave.size()).isEqualTo(1);
        bookRepository.delete(bookAfterSave.get());
        List<Comment> commentsAfterDelete = commentRepository.findAll();
        assertThat(commentsAfterDelete.size()).isEqualTo(4);
    }
}
