package ru.otus.mongo.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.mongo.service.AuthorService;
import ru.otus.mongo.service.BookService;
import ru.otus.mongo.service.CommentService;
import ru.otus.mongo.service.GenreService;

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
    public String getAuthorById(@ShellOption(defaultValue = "0") String authorId) {
        return authorService.getAuthorById(authorId);
    }

    @ShellMethod(value = "Get author by name", key = {"an", "get-author-name"})
    public String getAuthorByName(@ShellOption(defaultValue = "author0") String authorName) {
        return authorService.getAuthorByName(authorName);
    }

    // ------------------- Genre

    @ShellMethod(value = "Get all genres", key = {"ag", "all-genres"})
    public String getAllGenres() {
        return genreService.getAllGenre();
    }

    @ShellMethod(value = "Get genre by id", key = {"gi", "get-genre"})
    public String getGenreById(@ShellOption(defaultValue = "0") String genreId) {
        return genreService.getGenreById(genreId);
    }

    @ShellMethod(value = "Get genre by name", key = {"gn", "get-genre-name"})
    public String getGenreByName(@ShellOption(defaultValue = "genre0") String genreName) {
        return genreService.getGenreByName(genreName);
    }

    // ------------------- Comment

    @ShellMethod(value = "Get all comments", key = {"ac", "all-comments"})
    public String getAllComments() {
        return commentService.getAllComments();
    }

    @ShellMethod(value = "Get comment by id", key = {"ci", "get-comment"})
    public String getCommentById(@ShellOption(defaultValue = "0") String commentId) {
        return commentService.getCommentById(commentId);
    }

    @ShellMethod(value = "Delete comment by id", key = {"cd", "delete-comment"})
    public String deleteCommentById(@ShellOption(defaultValue = "0") String commentId) {
        return commentService.deleteComment(commentId);
    }

    @ShellMethod(value = "Update comment by id", key = {"cu", "update-comment"})
    public String updateCommentById(@ShellOption(defaultValue = "0") String commentId) {
        return commentService.updateCommentById(commentId);
    }

    @ShellMethod(value = "Add comment", key = {"ca", "add-comment"})
    public String addComment(@ShellOption(defaultValue = "Comment test") String commentTxt) {
        return commentService.addComment(commentTxt);
    }

    @ShellMethod(value = "Get comment by book id", key = {"cb", "comment-book"})
    public String getCommentByBookId(@ShellOption(defaultValue = "0") String bookId) {
        return commentService.getCommentByBookId(bookId);
    }

    @ShellMethod(value = "Get comment by book name", key = {"cbn", "comment-book-name"})
    public String getCommentByBookName(@ShellOption(defaultValue = "book0") String bookName) {
        return commentService.getCommentByBookName(bookName);
    }

    // ------------------- Book

    @ShellMethod(value = "Get all books", key = {"ab", "all-books"})
    public String getAllBooks() {
        return bookService.getAllBooks();
    }

    @ShellMethod(value = "Get book by id", key = {"bi", "get-book"})
    public String getBookById(@ShellOption(defaultValue = "0") String bookId) {
        return bookService.getBookById(bookId);
    }

    @ShellMethod(value = "Get book by Name", key = {"bn", "get-book-name"})
    public String getBookByName(@ShellOption(defaultValue = "0") String bookName) {
        return bookService.getBookByName(bookName);
    }

    @ShellMethod(value = "Delete book by id", key = {"bd", "delete-book"})
    public String deleteBookById(@ShellOption(defaultValue = "0") String bookId) {
        return bookService.deleteBook(bookId);
    }

    @ShellMethod(value = "Update book by id", key = {"bu", "update-book"})
    public String updateBookById(@ShellOption(defaultValue = "0") String bookId) {
        return bookService.updateBookById(bookId);
    }

    @ShellMethod(value = "Add book", key = {"ba", "add-book"})
    public String addBook(@ShellOption(defaultValue = "book test") String bookName) {
        return bookService.addBook(bookName);
    }

    @ShellMethod(value = "Delete author from book by book id", key = {"bad", "delete-author-book"})
    public String deleteAuthorByBookId(@ShellOption(defaultValue = "0") String bookId) {
        return bookService.deleteAuthorByBookId(bookId);
    }

    @ShellMethod(value = "Add author into book by book id", key = {"baa", "add-author-book"})
    public String addAuthorByBookId(@ShellOption(defaultValue = "0") String bookId) {
        return bookService.addAuthorByBookId(bookId);
    }
}
