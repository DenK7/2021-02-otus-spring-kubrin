package ru.otus.spring.mvc.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import ru.otus.spring.mvc.domain.Comment;

public interface CommentRepository extends MongoRepository<Comment, String> {

    void deleteByBookId(@Param("bookId") String bookId);
}
