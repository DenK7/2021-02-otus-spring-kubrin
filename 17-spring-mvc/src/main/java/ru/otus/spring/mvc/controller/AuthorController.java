package ru.otus.spring.mvc.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.mvc.dto.AuthorDto;
import ru.otus.spring.mvc.service.AuthorService;

import java.util.List;

@RestController
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @ApiOperation(value = "Получение списка авторов", response = AuthorDto.class)
    @GetMapping("/lib/authors")
    public ResponseEntity<List<AuthorDto>> listBooks() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }
}
