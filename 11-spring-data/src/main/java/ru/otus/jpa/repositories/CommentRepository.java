package ru.otus.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.otus.jpa.domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    void deleteByBook_Id (@Param("bookId") Long bookId);
}
