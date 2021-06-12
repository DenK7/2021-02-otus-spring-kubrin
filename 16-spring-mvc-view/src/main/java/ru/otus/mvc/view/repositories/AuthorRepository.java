package ru.otus.mvc.view.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.mvc.view.domain.Author;

public interface AuthorRepository extends MongoRepository<Author, String> {

}
