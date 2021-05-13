package ru.otus.orm.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.orm.domain.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Author> findAuthorById(Long id) {
        return Optional.ofNullable(entityManager.find(Author.class, id));
    }

    @Override
    public List<Author> findAllAuthors() {
        TypedQuery<Author> query = entityManager.createQuery("select s from Author s", Author.class);
        return query.getResultList();
    }
}
