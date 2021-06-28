package ru.otus.web.flux.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import ru.otus.web.flux.domain.Comment;

public interface CommentRepository extends ReactiveMongoRepository<Comment, String> {

    void deleteByBookId(@Param("bookId") String bookId);
}
