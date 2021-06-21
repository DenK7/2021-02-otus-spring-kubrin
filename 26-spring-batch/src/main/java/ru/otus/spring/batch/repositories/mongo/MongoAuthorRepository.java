package ru.otus.spring.batch.repositories.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.batch.domain.mongo.MongoAuthor;

public interface MongoAuthorRepository extends MongoRepository<MongoAuthor, String> {

}
