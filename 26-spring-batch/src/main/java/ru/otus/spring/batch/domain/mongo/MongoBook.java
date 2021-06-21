package ru.otus.spring.batch.domain.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document(value = "book")
public class MongoBook {

    @Id
    private String id;

    @Field(name = "book_name")
    private String bookName;

    private List<MongoAuthor> mongoAuthors;

    private MongoGenre mongoGenre;

}
