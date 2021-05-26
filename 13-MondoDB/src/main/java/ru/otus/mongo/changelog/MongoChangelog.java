package ru.otus.mongo.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.mongo.domain.Author;
import ru.otus.mongo.domain.Book;
import ru.otus.mongo.domain.Comment;
import ru.otus.mongo.domain.Genre;
import ru.otus.mongo.repositories.AuthorRepository;
import ru.otus.mongo.repositories.BookRepository;
import ru.otus.mongo.repositories.CommentRepository;
import ru.otus.mongo.repositories.GenreRepository;

import java.util.ArrayList;
import java.util.List;

@ChangeLog
public class MongoChangelog {

    private Author author1;
    private Author author2;
    private Author author3;
    private Genre genre1;
    private Genre genre2;
    private Book book1;
    private Book book2;


    @ChangeSet(order = "001", id = "dropDb", author = "kdv", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insertAuthors", author = "kdv")
    public void insertAuthors(AuthorRepository repository) {
        author1 = repository.save(Author.builder().authorName("author1").build());
        author2 = repository.save(Author.builder().authorName("author2").build());
        author3 = repository.save(Author.builder().authorName("author3").build());
    }

    @ChangeSet(order = "003", id = "insertGenres", author = "kdv")
    public void insertGenres(GenreRepository repository) {
        genre1 = repository.save(Genre.builder()
                .genreName("genre1")
                .build());
        genre2 = repository.save(Genre.builder()
                .genreName("genre2")
                .build());
    }

    @ChangeSet(order = "004", id = "insertBook1", author = "kdv")
    public void insertBook1(BookRepository repository) {
        List<Author> authors = new ArrayList<>();
        authors.add(author1);
        book1 = repository.save(Book.builder()
                .bookName("book1")
                .authors(authors)
                .genre(genre1)
                .build());
    }

    @ChangeSet(order = "005", id = "insertBook2", author = "kdv")
    public void insertBook2(BookRepository repository) {
        List<Author> authors = new ArrayList<>();
        authors.add(author2);
        authors.add(author3);
        book2 = repository.save(Book.builder()
                .bookName("book2")
                .authors(authors)
                .genre(genre2)
                .build());
    }

    @ChangeSet(order = "006", id = "insertComments", author = "kdv")
    public void insertComments(CommentRepository repository) {
        repository.save(Comment.builder()
                .commentTxt("comment1_book_1")
                .bookId(book1.getId())
                .build());
        repository.save(Comment.builder()
                .commentTxt("comment2_book_1")
                .bookId(book1.getId())
                .build());
        repository.save(Comment.builder()
                .commentTxt("comment1_book_2")
                .bookId(book2.getId())
                .build());
        repository.save(Comment.builder()
                .commentTxt("comment2_book_2")
                .bookId(book2.getId())
                .build());
    }

}
