package ru.otus.jdbc.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.jdbc.service.AuthorService;
import ru.otus.jdbc.service.BookService;

@ShellComponent
public class ShellCommands {

    private final AuthorService authorService;
    private final BookService bookService;

    public ShellCommands(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @ShellMethod(value = "Get all authors", key = {"al", "all-authors"})
    public String getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @ShellMethod(value = "Add authors", key = {"aa", "add-author"})
    public String addAuthor(@ShellOption(defaultValue = "Author") String authorName) {
        return authorService.addAuthor(authorName);
    }

    // метод только для проверки систояния бд
    @ShellMethod(value = "Get all books", key = {"ab", "all-books"})
    public String getAllBooks() {
        return bookService.getAllBooks();
    }

    @ShellMethod(value = "Get book", key = {"b", "get-book"})
    public String getBook(@ShellOption(defaultValue = "0") Long bookId) {
        return bookService.getBookById(bookId);
    }

    @ShellMethod(value = "Delete book", key = {"d", "delete-book"})
    public String delBook(@ShellOption(defaultValue = "0") Long bookId) {
        return bookService.deleteBookById(bookId);
    }

    @ShellMethod(value = "Add book", key = {"ib", "insert-book"})
    public String addBook(@ShellOption(defaultValue = "Book name") String bookName) {
        return bookService.addBook(bookName);
    }

    @ShellMethod(value = "Update book", key = {"ub", "update-book"})
    public String updBook(@ShellOption(defaultValue = "0") Long bookId) {
        return bookService.updBook(bookId);
    }
}
