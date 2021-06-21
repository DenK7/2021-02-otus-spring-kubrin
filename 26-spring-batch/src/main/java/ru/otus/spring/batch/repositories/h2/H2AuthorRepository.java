package ru.otus.spring.batch.repositories.h2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.batch.domain.h2.H2Author;

import java.util.Optional;

@Repository
public interface H2AuthorRepository extends JpaRepository<H2Author, Long> {

    Optional<H2Author> findByMongoId(String mongoId);
}
