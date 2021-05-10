package ru.otus.orm.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.orm.service.AuthorService;
import ru.otus.orm.service.GenreService;

@ShellComponent
public class ShellCommands {

    private final AuthorService authorService;
    private final GenreService genreService;

    public ShellCommands(AuthorService authorService, GenreService genreService) {
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @ShellMethod(value = "Get all authors", key = {"al", "all-authors"})
    public String getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @ShellMethod(value = "Get author by id", key = {"ai", "get-author"})
    public String getAuthorById(@ShellOption(defaultValue = "0") Long authorId) {
        return authorService.getAuthorById(authorId);
    }

    @ShellMethod(value = "Get all genres", key = {"ag", "all-genres"})
    public String getAllGenres() {
        return genreService.getAllGenre();
    }

    @ShellMethod(value = "Get genre by id", key = {"gi", "get-genre"})
    public String getGenreById(@ShellOption(defaultValue = "0") Long genreId) {
        return genreService.getGenreById(genreId);
    }

//
//    @ShellMethod(value = "Add authors", key = {"aa", "add-author"})
//    public String addAuthor(@ShellOption(defaultValue = "Author") String authorName) {
//        return authorService.addAuthor(authorName);
//    }
//
//    // метод только для проверки систояния бд
//    @ShellMethod(value = "Get all books", key = {"ab", "all-books"})
//    public String getAllBooks() {
//        return bookService.getAllBooks();
//    }
//
//    @ShellMethod(value = "Get book", key = {"b", "get-book"})
//    public String getBook(@ShellOption(defaultValue = "0") Long bookId) {
//        return bookService.getBookById(bookId);
//    }
//
//    @ShellMethod(value = "Delete book", key = {"d", "delete-book"})
//    public String delBook(@ShellOption(defaultValue = "0") Long bookId) {
//        return bookService.deleteBookById(bookId);
//    }
//
//    @ShellMethod(value = "Add book", key = {"ib", "insert-book"})
//    public String addBook(@ShellOption(defaultValue = "Book name") String bookName) {
//        return bookService.addBook(bookName);
//    }
//
//    @ShellMethod(value = "Update book", key = {"ub", "update-book"})
//    public String updBook(@ShellOption(defaultValue = "0") Long bookId) {
//        return bookService.updBook(bookId);
//    }
}
