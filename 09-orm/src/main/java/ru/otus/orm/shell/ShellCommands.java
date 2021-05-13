package ru.otus.orm.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.orm.service.AuthorService;
import ru.otus.orm.service.BookService;
import ru.otus.orm.service.CommentService;
import ru.otus.orm.service.GenreService;

@ShellComponent
public class ShellCommands {

    private final AuthorService authorService;
    private final GenreService genreService;
    private final CommentService commentService;
    private final BookService bookService;

    public ShellCommands(AuthorService authorService, GenreService genreService, CommentService commentService, BookService bookService) {
        this.authorService = authorService;
        this.genreService = genreService;
        this.commentService = commentService;
        this.bookService = bookService;
    }

    // ------------------- Author

    @ShellMethod(value = "Get all authors", key = {"al", "all-authors"})
    public String getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @ShellMethod(value = "Get author by id", key = {"ai", "get-author"})
    public String getAuthorById(@ShellOption(defaultValue = "0") Long authorId) {
        return authorService.getAuthorById(authorId);
    }

    // ------------------- Genre

    @ShellMethod(value = "Get all genres", key = {"ag", "all-genres"})
    public String getAllGenres() {
        return genreService.getAllGenre();
    }

    @ShellMethod(value = "Get genre by id", key = {"gi", "get-genre"})
    public String getGenreById(@ShellOption(defaultValue = "0") Long genreId) {
        return genreService.getGenreById(genreId);
    }

    // ------------------- Comment

    @ShellMethod(value = "Get all comments", key = {"ac", "all-comments"})
    public String getAllComments() {
        return commentService.getAllComments();
    }

    @ShellMethod(value = "Get comment by id", key = {"ci", "get-comment"})
    public String getCommentById(@ShellOption(defaultValue = "0") Long commentId) {
        return commentService.getCommentById(commentId);
    }

    @ShellMethod(value = "Delete comment by id", key = {"cd", "delete-comment"})
    public String deleteCommentById(@ShellOption(defaultValue = "0") Long commentId) {
        return commentService.deleteCommentById(commentId);
    }

    @ShellMethod(value = "Update comment by id", key = {"cu", "update-comment"})
    public String updateCommentById(@ShellOption(defaultValue = "0") Long commentId) {
        return commentService.updateCommentById(commentId);
    }

    @ShellMethod(value = "Add comment", key = {"ca", "add-comment"})
    public String addComment(@ShellOption(defaultValue = "Comment test") String commentTxt) {
        return commentService.addComment(commentTxt);
    }

    // ------------------- Book

    @ShellMethod(value = "Get all books", key = {"ab", "all-books"})
    public String getAllBooks() {
        return bookService.getAllBooks();
    }

    @ShellMethod(value = "Get book by id", key = {"bi", "get-book"})
    public String getBookById(@ShellOption(defaultValue = "0") Long bookId) {
        return bookService.getBookById(bookId);
    }

    @ShellMethod(value = "Delete book by id", key = {"bd", "delete-book"})
    public String deleteBookById(@ShellOption(defaultValue = "0") Long bookId) {
        return bookService.deleteBookById(bookId);
    }

    @ShellMethod(value = "Update book by id", key = {"bu", "update-book"})
    public String updateBookById(@ShellOption(defaultValue = "0") Long bookId) {
        return bookService.updateBookById(bookId);
    }

    @ShellMethod(value = "Add book", key = {"ba", "add-book"})
    public String addBook(@ShellOption(defaultValue = "book test") String bookName) {
        return bookService.addBook(bookName);
    }

    @ShellMethod(value = "Delete author from book by book id", key = {"bad", "delete-author-book"})
    public String deleteAuthorByBookId(@ShellOption(defaultValue = "0") Long bookId) {
        return bookService.deleteAuthorByBookId(bookId);
    }

    @ShellMethod(value = "Add author into book by book id", key = {"baa", "add-author-book"})
    public String addAuthorByBookId(@ShellOption(defaultValue = "0") Long bookId) {
        return bookService.addAuthorByBookId(bookId);
    }
}
