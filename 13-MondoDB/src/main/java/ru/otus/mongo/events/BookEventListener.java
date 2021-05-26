package ru.otus.mongo.events;

import org.bson.Document;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;
import ru.otus.mongo.domain.Book;
import ru.otus.mongo.repositories.CommentRepository;

@Component
public class BookEventListener extends AbstractMongoEventListener<Book> {

    private final CommentRepository commentRepository;


    public BookEventListener(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Book> event) {
        super.onBeforeDelete(event);
        Document source = event.getSource();
        String bookId = source.get("_id").toString();
        commentRepository.deleteByBookId(bookId);
    }
}
