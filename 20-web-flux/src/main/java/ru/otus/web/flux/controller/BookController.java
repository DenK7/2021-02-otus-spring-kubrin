package ru.otus.web.flux.controller;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.web.flux.dto.BookDto;
import ru.otus.web.flux.dto.convertor.BookConvertor;
import ru.otus.web.flux.repository.BookRepository;

@RestController
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/books")
    public Flux<BookDto> listBooks() {
        return bookRepository.findAll()
                .map(BookConvertor::toBookDto);
    }

    @PostMapping("/book")
    public Mono<BookDto> saveBook(
            @RequestBody BookDto book) {
        return bookRepository.save(BookConvertor.toBook(book))
                .flatMap(savedBook -> Mono.just(BookConvertor.toBookDto(savedBook)));
    }

    @GetMapping("/book")
    public Mono<BookDto> getBook(
            @RequestBody String id) {

        return bookRepository.findById(id).flatMap(book -> Mono.just(BookConvertor.toBookDto(book)));
    }

    @DeleteMapping("/book")
    public Mono<Void> deleteBook(
            @RequestBody String id) {
        return bookRepository.deleteById(id);
    }
}
