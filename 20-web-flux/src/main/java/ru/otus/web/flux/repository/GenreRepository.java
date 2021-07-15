package ru.otus.web.flux.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.web.flux.domain.Genre;

public interface GenreRepository extends ReactiveMongoRepository<Genre, String> {

}
