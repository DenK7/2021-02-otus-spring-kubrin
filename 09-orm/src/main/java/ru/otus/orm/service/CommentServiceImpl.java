package ru.otus.orm.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.orm.domain.Book;
import ru.otus.orm.domain.Comment;
import ru.otus.orm.exception.InputNotCorrectException;
import ru.otus.orm.repositories.BookRepository;
import ru.otus.orm.repositories.CommentRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

import static ru.otus.orm.service.CommonService.getValue;

@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;

    public CommentServiceImpl(CommentRepository commentRepository, BookRepository bookRepository) {
        this.commentRepository = commentRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public String getAllComments() {
        List<Comment> comments = commentRepository.findAllComments();
        StringBuilder builder = new StringBuilder();
        for (Comment comment: comments) {
            if (builder.length() > 0) {
                builder.append("; ");
            }
            builder.append(mapComment(comment));
        }
        return builder.toString();
    }

    @Override
    public String getCommentById(Long id) {
        Optional<Comment> commentOptional = commentRepository.findCommentById(id);
        return commentOptional
                .map(genre -> mapComment(genre))
                .orElse("Genre not found");
    }

    @Override
    @Transactional
    public String deleteCommentById(Long id) {
        commentRepository.deleteCommentById(id);
        return "Comment deleted";
    }

    @Override
    @Transactional
    public String updateCommentById(Long id) {
        Optional<Comment> commentOptional = commentRepository.findCommentById(id);
        return commentOptional
                .map(this::changeTxtAndUpdateComment)
                .orElse("Genre not found");
    }

    @Override
    @Transactional
    public String addComment(String txt) {
        if (txt.trim().length() == 0) {
            return "Comment is not correct";
        }

        String val = getValue("Input book id");
        Long bookId;
        try {
            bookId = Long.valueOf(val);
        } catch (Exception e) {
            return "Book id is not correct";
        }

        Optional<Book> bookOptional = bookRepository.findBookById(bookId);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            Comment comment = Comment.builder().commentTxt(txt).book(book).build();
            try {
                Comment savedComment = commentRepository.saveComment(comment);
                book.getComments().add(savedComment);
                return mapComment(savedComment);
            } catch (Exception e) {
                return "Comment not add";
            }
        } else {
            return "Book id is not correct";
        }
    }

    private String changeTxtAndUpdateComment(Comment comment) {
        try {
            String txt = getValue("Input comment:");
            comment.setCommentTxt(txt);
            commentRepository.updateCommentById(comment.getId(), txt);
            return "Comment updated";
        } catch (InputNotCorrectException e) {
            return "Comment is not correct";
        }
    }

    private String mapComment(Comment comment) {
        return "id = " + comment.getId() + ", comment = " + comment.getCommentTxt()
                + ", book id = " + comment.getBook().getId() + ", book name = " + comment.getBook().getBookName();
    }
}
