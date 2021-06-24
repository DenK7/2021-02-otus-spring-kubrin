package ru.otus.spring.mvc.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.mvc.domain.Genre;

public interface GenreRepository extends MongoRepository<Genre, String> {

}
