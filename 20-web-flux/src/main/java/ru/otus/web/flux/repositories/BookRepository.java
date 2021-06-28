package ru.otus.web.flux.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Mono;
import ru.otus.web.flux.domain.Book;

public interface BookRepository extends ReactiveMongoRepository<Book, String> {

    Mono<Void> deleteById(@Param("id") String id);
}
