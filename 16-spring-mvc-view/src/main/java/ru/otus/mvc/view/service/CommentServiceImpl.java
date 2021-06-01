package ru.otus.mvc.view.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.mvc.view.domain.Book;
import ru.otus.mvc.view.domain.Comment;
import ru.otus.mvc.view.exception.InputNotCorrectException;
import ru.otus.mvc.view.repositories.BookRepository;
import ru.otus.mvc.view.repositories.CommentRepository;

import java.util.List;
import java.util.Optional;

import static ru.otus.mvc.view.service.CommonService.getValue;

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
        return mapComments(comments);
    }

    @Override
    public String getCommentById(String id) {
        Optional<Comment> commentOptional = commentRepository.findById(id);
        return commentOptional
                .map(this::mapComment)
                .orElse("Comment not found");
    }

    @Override
    @Transactional
    public String deleteComment(String id) {
        var commentOptional = commentRepository.findById(id);
        if (commentOptional.isPresent()) {
            commentRepository.delete(commentOptional.get());
            return "Comment deleted";
        }
        return "Comment not found";
    }

    @Override
    @Transactional
    public String updateCommentById(String id) {
        var commentOptional = commentRepository.findById(id);
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

        String bookId = getValue("Input book id");

        var bookOptional = bookRepository.findById(bookId);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            Comment comment = Comment.builder().commentTxt(txt).bookId(book.getId()).build();
            try {
                Comment savedComment = commentRepository.save(comment);
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
    public String getCommentByBookId(String bookId) {
        List<Comment> comments = commentRepository.findByBookId(bookId);
        return mapComments(comments);
    }

    @Override
    public String getCommentByBookName(String bookName) {
        Optional<Book> bookOptional = bookRepository.findByBookName(bookName);
        if (bookOptional.isEmpty()) {
            return "Book not found";
        }
        List<Comment> comments = commentRepository.findByBookId(bookOptional.get().getId());
        return mapComments(comments);
    }

    private String changeTxtAndUpdateComment(Comment comment) {
        try {
            String txt = getValue("Input comment:");
            comment.setCommentTxt(txt);
            commentRepository.save(comment);
            return "Comment updated";
        } catch (InputNotCorrectException e) {
            return "Comment is not correct";
        }
    }

    private String mapComments(List<Comment> comments) {
        StringBuilder builder = new StringBuilder();
        for (Comment comment: comments) {
            if (builder.length() > 0) {
                builder.append("; ");
            }
            builder.append(mapComment(comment));
        }
        return builder.toString();
    }

    private String mapComment(Comment comment) {
        return "id = " + comment.getId() + ", comment = " + comment.getCommentTxt()
                + ", book id = " + comment.getBookId();
    }
}
