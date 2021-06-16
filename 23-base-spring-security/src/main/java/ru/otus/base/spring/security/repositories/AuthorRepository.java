package ru.otus.base.spring.security.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.base.spring.security.domain.Author;

public interface AuthorRepository extends MongoRepository<Author, String> {

}
