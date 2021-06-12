package ru.otus.mvc.view.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.mvc.view.domain.Book;

public interface BookRepository extends MongoRepository<Book, String> {

}
