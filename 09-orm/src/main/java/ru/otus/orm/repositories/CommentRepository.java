package ru.otus.orm.repositories;

import ru.otus.orm.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {

    Comment saveComment(Comment comment);
    Optional<Comment> findCommentById(Long id);
    List<Comment> findAllComments();
    void updateCommentById(Long id, String txt);
    void deleteComment(Comment comment);
    void deleteCommentsByBookId(Long id);

}
