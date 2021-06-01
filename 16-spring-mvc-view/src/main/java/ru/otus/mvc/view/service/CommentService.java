package ru.otus.mvc.view.service;

public interface CommentService {
    String getAllComments();
    String getCommentById(String id);
    String addComment(String txt);
    String updateCommentById(String id);
    String deleteComment(String id);

    String getCommentByBookId(String bookId);
    String getCommentByBookName(String bookName);
}
