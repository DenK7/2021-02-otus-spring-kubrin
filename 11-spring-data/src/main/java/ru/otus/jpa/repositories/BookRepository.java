package ru.otus.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.otus.jpa.domain.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("select count(e) from Book e where e.id = :id")
    Long countById(@Param("id") Long id);

}
