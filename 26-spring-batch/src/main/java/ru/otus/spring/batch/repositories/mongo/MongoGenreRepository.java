package ru.otus.spring.batch.repositories.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.batch.domain.mongo.MongoGenre;

public interface MongoGenreRepository extends MongoRepository<MongoGenre, String> {

}
