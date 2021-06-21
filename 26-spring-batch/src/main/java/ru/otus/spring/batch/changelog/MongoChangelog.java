package ru.otus.spring.batch.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.spring.batch.domain.mongo.MongoAuthor;
import ru.otus.spring.batch.domain.mongo.MongoBook;
import ru.otus.spring.batch.domain.mongo.MongoGenre;
import ru.otus.spring.batch.repositories.mongo.MongoAuthorRepository;
import ru.otus.spring.batch.repositories.mongo.MongoBookRepository;
import ru.otus.spring.batch.repositories.mongo.MongoGenreRepository;

import java.util.ArrayList;
import java.util.List;

@ChangeLog
public class MongoChangelog {

    private MongoAuthor mongoAuthor1;
    private MongoAuthor mongoAuthor2;
    private MongoAuthor mongoAuthor3;
    private MongoGenre mongoGenre1;
    private MongoGenre mongoGenre2;
    private MongoBook mongoBook1;
    private MongoBook mongoBook2;


    @ChangeSet(order = "001", id = "dropDb", author = "kdv", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insertAuthors", author = "kdv")
    public void insertAuthors(MongoAuthorRepository repository) {
        mongoAuthor1 = repository.save(MongoAuthor.builder().authorName("author1").build());
        mongoAuthor2 = repository.save(MongoAuthor.builder().authorName("author2").build());
        mongoAuthor3 = repository.save(MongoAuthor.builder().authorName("author3").build());
    }

    @ChangeSet(order = "003", id = "insertGenres", author = "kdv")
    public void insertGenres(MongoGenreRepository repository) {
        mongoGenre1 = repository.save(MongoGenre.builder()
                .genreName("genre1")
                .build());
        mongoGenre2 = repository.save(MongoGenre.builder()
                .genreName("genre2")
                .build());
    }

    @ChangeSet(order = "004", id = "insertBook1", author = "kdv")
    public void insertBook1(MongoBookRepository repository) {
        List<MongoAuthor> mongoAuthors = new ArrayList<>();
        mongoAuthors.add(mongoAuthor1);
        MongoBook mongoBook = new MongoBook();
        mongoBook.setBookName("book1");
        mongoBook.setMongoAuthors(mongoAuthors);
        mongoBook.setMongoGenre(mongoGenre1);
        mongoBook1 = repository.save(mongoBook);
    }

    @ChangeSet(order = "005", id = "insertBook2", author = "kdv")
    public void insertBook2(MongoBookRepository repository) {
        List<MongoAuthor> mongoAuthors = new ArrayList<>();
        mongoAuthors.add(mongoAuthor2);
        mongoAuthors.add(mongoAuthor3);
        MongoBook mongoBook = new MongoBook();
        mongoBook.setBookName("book2");
        mongoBook.setMongoAuthors(mongoAuthors);
        mongoBook.setMongoGenre(mongoGenre2);
        mongoBook2 = repository.save(mongoBook);
    }
}
