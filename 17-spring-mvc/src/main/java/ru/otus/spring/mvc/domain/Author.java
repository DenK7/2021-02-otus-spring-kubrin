package ru.otus.spring.mvc.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(value = "author")
@Builder
public class Author {
    @Id
    private String id;

    @Field(name = "author_name")
    private String authorName;

    @Override
    public String toString() {
        return "id = " + id + ", authorName = " + authorName ;
    }
}
