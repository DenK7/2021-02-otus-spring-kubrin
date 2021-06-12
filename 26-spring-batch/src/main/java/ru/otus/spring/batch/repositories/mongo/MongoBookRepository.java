package ru.otus.spring.batch.repositories.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.batch.domain.mongo.MongoBook;

public interface MongoBookRepository extends MongoRepository<MongoBook, String> {

}
