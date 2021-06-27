package ru.otus.spring.mvc.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.mvc.domain.Book;

public interface BookRepository extends MongoRepository<Book, String> {

}
