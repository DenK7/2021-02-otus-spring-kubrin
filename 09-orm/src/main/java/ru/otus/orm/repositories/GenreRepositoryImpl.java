package ru.otus.orm.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.orm.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class GenreRepositoryImpl implements GenreRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Genre> findGenreById(Long id) {
        return Optional.ofNullable(entityManager.find(Genre.class, id));
    }

    @Override
    public List<Genre> findAllGenres() {
        TypedQuery<Genre> query = entityManager.createQuery("select s from Genre s", Genre.class);
        return query.getResultList();
    }
}
