package ru.otus.web.flux.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.otus.web.flux.dto.AuthorDto;
import ru.otus.web.flux.dto.convertor.AuthorConvertor;
import ru.otus.web.flux.repository.AuthorRepository;

@RestController
public class AuthorController {

    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping("/authors")
    public Flux<AuthorDto> listBooks() {
        return authorRepository.findAll().
                map(AuthorConvertor::toAuthorDto);
    }
}
