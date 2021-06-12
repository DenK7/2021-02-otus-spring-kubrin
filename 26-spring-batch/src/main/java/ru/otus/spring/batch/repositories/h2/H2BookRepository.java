package ru.otus.spring.batch.repositories.h2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.batch.domain.h2.H2Book;

@Repository
public interface H2BookRepository extends JpaRepository<H2Book, Long> {

}
