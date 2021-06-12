package ru.otus.base.spring.security.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.base.spring.security.domain.Book;

public interface BookRepository extends MongoRepository<Book, String> {

}
