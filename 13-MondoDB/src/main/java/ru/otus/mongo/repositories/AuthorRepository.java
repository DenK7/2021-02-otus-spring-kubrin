package ru.otus.mongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import ru.otus.mongo.domain.Author;

public interface AuthorRepository extends MongoRepository<Author, String> {

    Author findAuthorByAuthorName(@Param("authorName") String authorName);
}
