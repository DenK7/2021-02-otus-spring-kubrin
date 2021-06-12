package ru.otus.base.spring.security.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import ru.otus.base.spring.security.domain.Comment;

public interface CommentRepository extends MongoRepository<Comment, String> {

    void deleteByBookId(@Param("bookId") String bookId);
}
