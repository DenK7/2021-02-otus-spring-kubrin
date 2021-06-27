package ru.otus.spring.mvc.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.mvc.dto.GenreDto;
import ru.otus.spring.mvc.service.GenreService;

import java.util.List;

@RestController
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @ApiOperation(value = "Получение списка жанров", response = GenreDto.class)
    @GetMapping("/lib/genres")
    public ResponseEntity<List<GenreDto>> listBooks() {
        return ResponseEntity.ok(genreService.getAllGenre());
    }
}

