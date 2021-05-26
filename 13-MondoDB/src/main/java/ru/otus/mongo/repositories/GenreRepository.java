package ru.otus.mongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import ru.otus.mongo.domain.Genre;

public interface GenreRepository extends MongoRepository<Genre, String> {

    Genre findGenreByGenreName(@Param("genreName") String genreName);

}
