package ru.otus.base.spring.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.base.spring.security.domain.Author;
import ru.otus.base.spring.security.domain.Book;
import ru.otus.base.spring.security.domain.Genre;
import ru.otus.base.spring.security.service.AuthorService;
import ru.otus.base.spring.security.service.BookService;
import ru.otus.base.spring.security.service.GenreService;

import java.util.List;

@Controller
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    public BookController(BookService bookService, AuthorService authorService, GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @GetMapping("/")
    public String listBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "list_books";
    }

    @GetMapping("/book_edit")
    public String editBook(@RequestParam("id") String id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute(book);
        List<Author> authors = authorService.getAllAuthors();
        model.addAttribute("authors", authors);
        List<Genre> genres = genreService.getAllGenre();
        model.addAttribute("genres", genres);
        return "book_edit";
    }

    @PostMapping("/book_edit")
    public String editBook(Book book) {
        bookService.updateBook(book);
        return "redirect:/";
    }

    @GetMapping("/book_add")
    public String addBook(Book book, Model model) {
        List<Author> authors = authorService.getAllAuthors();
        model.addAttribute("authors", authors);
        List<Genre> genres = genreService.getAllGenre();
        model.addAttribute("genres", genres);
        return "book_add";
    }

    @PostMapping("/book_add")
    public String addBook(Book book) {
        bookService.addBook(book);
        return "redirect:/";
    }

    @GetMapping("/book_delete")
    public String deleteBook(String id) {
        bookService.deleteBook(id);
        return "redirect:/";
    }

}
