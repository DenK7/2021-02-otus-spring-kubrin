package ru.otus.orm.service;

public interface CommentService {
    String getAllComments();
    String getCommentById(Long id);
    String addComment(String txt);
    String updateCommentById(Long id);
    String deleteCommentById(Long id);
}
