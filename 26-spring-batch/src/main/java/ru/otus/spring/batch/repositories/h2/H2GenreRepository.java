package ru.otus.spring.batch.repositories.h2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.batch.domain.h2.H2Genre;

import java.util.Optional;

@Repository
public interface H2GenreRepository extends JpaRepository<H2Genre, Long> {

    Optional<H2Genre> findByMongoId(String mongoId);
}
