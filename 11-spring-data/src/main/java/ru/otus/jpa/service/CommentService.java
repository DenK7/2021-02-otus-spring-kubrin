package ru.otus.jpa.service;

public interface CommentService {
    String getAllComments();
    String getCommentById(Long id);
    String addComment(String txt);
    String updateCommentById(Long id);
    String deleteComment(Long id);

    String getCommentByBookId(Long bookId);
}
