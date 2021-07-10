package ru.otus.spring.mvc.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.spring.mvc.domain.*;
import ru.otus.spring.mvc.repositories.*;

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
        author1 = repository.save(Author.builder().id("1").authorName("author1").build());
        author2 = repository.save(Author.builder().id("2").authorName("author2").build());
        author3 = repository.save(Author.builder().id("3").authorName("author3").build());
    }

    @ChangeSet(order = "003", id = "insertGenres", author = "kdv")
    public void insertGenres(GenreRepository repository) {
        genre1 = repository.save(Genre.builder()
                .id("1")
                .genreName("genre1")
                .build());
        genre2 = repository.save(Genre.builder()
                .id("2")
                .genreName("genre2")
                .build());
    }

    @ChangeSet(order = "004", id = "insertBook1", author = "kdv")
    public void insertBook1(BookRepository repository) {
        List<Author> authors = new ArrayList<>();
        authors.add(author1);
        Book book = new Book();
        book.setId("1");
        book.setBookName("book1");
        book.setAuthors(authors);
        book.setGenre(genre1);
        book1 = repository.save(book);
    }

    @ChangeSet(order = "005", id = "insertBook2", author = "kdv")
    public void insertBook2(BookRepository repository) {
        List<Author> authors = new ArrayList<>();
        authors.add(author2);
        authors.add(author3);
        Book book = new Book();
        book.setId("2");
        book.setBookName("book2");
        book.setAuthors(authors);
        book.setGenre(genre2);
        book2 = repository.save(book);
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

    @ChangeSet(order = "007", id = "insertUsers", author = "kdv")
    public void insertUsers(UserRepository repository) {
        repository.save(User.builder().userName("user").password("1").role("USER").build());
        repository.save(User.builder().userName("admin").password("1").role("ADMIN").build());
    }
}
