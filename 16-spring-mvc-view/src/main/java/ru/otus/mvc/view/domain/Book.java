package ru.otus.mvc.view.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document(value = "book")
@Builder
public class Book {
    @Id
    private String id;

    @Field(name = "book_name")
    private String bookName;

    private List<Author> authors;

    private Genre genre;
}
