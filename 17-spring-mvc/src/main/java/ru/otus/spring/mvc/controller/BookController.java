package ru.otus.spring.mvc.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.mvc.dto.BookDto;
import ru.otus.spring.mvc.service.BookService;

import java.util.List;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @ApiOperation(value = "Получение списка книг", response = BookDto.class)
    @GetMapping("/books")
    public ResponseEntity<List<BookDto>> listBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @PostMapping("/book")
    @ApiOperation(value = "Сохранение книги", response = BookDto.class)
    public ResponseEntity<BookDto> saveBook(
            @ApiParam(value = "Книга для сохранения", example = "0")
            @RequestBody BookDto book) {
        return ResponseEntity.ok(bookService.saveBook(book));
    }

    @GetMapping("/book")
    @ApiOperation(value = "Получение книги", response = BookDto.class)
    public ResponseEntity<BookDto> getBook(
            @ApiParam(value = "id книги", example = "0")
            @RequestBody String id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @DeleteMapping("/book")
    @ApiOperation(value = "Удаление книги")
    public ResponseEntity<String> deleteBook(
            @ApiParam(value = "id книги", example = "0")
            @RequestBody String id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok(id);
    }
}
