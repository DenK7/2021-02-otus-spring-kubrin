package ru.otus.orm.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.orm.domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepositoryImpl implements BookRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Book saveBook(Book book) {
        if (book.getId() == null) {
            entityManager.persist(book);
            return book;
        } else {
            return entityManager.merge(book);
        }
    }

    @Override
    public Optional<Book> findBookById(Long id) {
        return Optional.ofNullable(entityManager.find(Book.class, id));
    }

    @Override
    public List<Book> findAllBooks() {
        TypedQuery<Book> query = entityManager.createQuery("select s from Book s", Book.class);
        return query.getResultList();
    }

    @Override
    public void deleteBookById(Long id) {
        Query query = entityManager.createQuery("delete from Book where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public BigInteger countBookById(Long id) {
        Query query = entityManager.createNativeQuery("select count(s.id) from Book s where s.id = :id");
        query.setParameter("id", id);
        return (BigInteger) query.getSingleResult();
    }
}
