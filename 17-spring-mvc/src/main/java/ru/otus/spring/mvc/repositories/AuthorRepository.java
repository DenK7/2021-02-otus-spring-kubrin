package ru.otus.spring.mvc.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.mvc.domain.Author;

public interface AuthorRepository extends MongoRepository<Author, String> {

}
