package ru.otus.jpa.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.jpa.domain.Book;
import ru.otus.jpa.domain.Comment;
import ru.otus.jpa.exception.InputNotCorrectException;
import ru.otus.jpa.repositories.BookRepository;
import ru.otus.jpa.repositories.CommentRepository;

import java.util.List;
import java.util.Optional;

import static ru.otus.jpa.service.CommonService.getValue;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;

    public CommentServiceImpl(CommentRepository commentRepository, BookRepository bookRepository) {
        this.commentRepository = commentRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public String getAllComments() {
        List<Comment> comments = commentRepository.findAll();
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
        Optional<Comment> commentOptional = commentRepository.findById(id);
        return commentOptional
                .map(this::mapComment)
                .orElse("Comment not found");
    }

    @Override
    @Transactional
    public String deleteComment(Long id) {
        Optional<Comment> commentOptional = commentRepository.findById(id);
        if (commentOptional.isPresent()) {
            commentRepository.delete(commentOptional.get());
            return "Comment deleted";
        }
        return "Comment not found";
    }

    @Override
    @Transactional
    public String updateCommentById(Long id) {
        Optional<Comment> commentOptional = commentRepository.findById(id);
        return commentOptional
                .map(this::changeTxtAndUpdateComment)
                .orElse("Comment not found");
    }

    @Override
    @Transactional
    public String addComment(String txt) {
        if (txt.trim().length() == 0) {
            return "Comment is not correct";
        }

        String val = getValue("Input book id");
        long bookId;
        try {
            bookId = Long.parseLong(val);
        } catch (Exception e) {
            return "Book id is not correct";
        }

        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            Comment comment = Comment.builder().commentTxt(txt).book(book).build();
            try {
                Comment savedComment = commentRepository.saveAndFlush(comment);
                book.getComments().add(savedComment);
                return mapComment(savedComment);
            } catch (Exception e) {
                //тут желательно выдавать stack trace
                return "Comment not add";
            }
        } else {
            return "Book id is not correct";
        }
    }

    @Override
    @Transactional(readOnly = true)
    public String getCommentByBookId(Long bookId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (bookOptional.isEmpty()) {
            return "Book not found";
        }

        StringBuilder builder = new StringBuilder();
        for (Comment comment: bookOptional.get().getComments()) {
            if (builder.length() > 0) {
                builder.append("; ");
            }
            builder.append(mapComment(comment));
        }
        return builder.toString();
    }

    private String changeTxtAndUpdateComment(Comment comment) {
        try {
            String txt = getValue("Input comment:");
            comment.setCommentTxt(txt);
            commentRepository.saveAndFlush(comment);
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
