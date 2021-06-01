package ru.otus.mvc.view.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import ru.otus.mvc.view.domain.Author;

public interface AuthorRepository extends MongoRepository<Author, String> {

    Author findAuthorByAuthorName(@Param("authorName") String authorName);
}
