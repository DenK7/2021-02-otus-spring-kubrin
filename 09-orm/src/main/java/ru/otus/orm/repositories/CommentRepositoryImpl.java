package ru.otus.orm.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.orm.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class CommentRepositoryImpl implements CommentRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Comment saveComment(Comment comment) {
        if (comment.getId() == null) {
            entityManager.persist(comment);
            return comment;
        } else {
            return entityManager.merge(comment);
        }
    }

    @Override
    public Optional<Comment> findCommentById(Long id) {
        return Optional.ofNullable(entityManager.find(Comment.class, id));
    }

    @Override
    public List<Comment> findAllComments() {
        TypedQuery<Comment> query = entityManager.createQuery("select s from Comment s", Comment.class);
        return query.getResultList();
    }

    @Override
    // книга у комментария не может быть изменена
    public void updateCommentById(Long id, String txt) {
        Query query = entityManager.createQuery("update Comment s " +
                "set s.commentTxt = :txt " +
                "where s.id = :id");
        query.setParameter("txt", txt);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void deleteComment(Comment comment) {
        entityManager.remove(comment);
    }

    @Override
    public void deleteCommentsByBookId(Long id) {
        Query query = entityManager.createQuery("delete from Comment s where s.book.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
