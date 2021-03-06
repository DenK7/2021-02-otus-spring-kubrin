package ru.otus.spring.batch.domain.mongo;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(value = "author")
@Builder
public class MongoAuthor {
    @Id
    private String id;

    @Field(name = "author_name")
    private String authorName;

    @Override
    public String toString() {
        return "id = " + id + ", authorName = " + authorName ;
    }
}
