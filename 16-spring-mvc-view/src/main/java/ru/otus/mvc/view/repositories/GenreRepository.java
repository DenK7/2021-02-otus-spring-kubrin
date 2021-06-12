package ru.otus.mvc.view.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.mvc.view.domain.Genre;

public interface GenreRepository extends MongoRepository<Genre, String> {

}
