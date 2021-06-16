package ru.otus.base.spring.security.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.base.spring.security.domain.Genre;

public interface GenreRepository extends MongoRepository<Genre, String> {

}
