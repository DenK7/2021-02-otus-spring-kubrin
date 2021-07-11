package ru.otus.web.flux.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.web.flux.domain.Author;

public interface AuthorRepository extends ReactiveMongoRepository<Author, String> {

}
