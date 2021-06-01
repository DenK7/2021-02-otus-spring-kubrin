package ru.otus.mvc.view.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import ru.otus.mvc.view.domain.Genre;

public interface GenreRepository extends MongoRepository<Genre, String> {

    Genre findGenreByGenreName(@Param("genreName") String genreName);

}
