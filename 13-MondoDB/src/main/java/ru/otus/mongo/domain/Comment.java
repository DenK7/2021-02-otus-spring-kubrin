package ru.otus.mongo.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(value = "comment")
@Builder
public class Comment {
    @Id
    private String id;

    @Field(name = "comment_txt")
    private String commentTxt;

    private String bookId;
}
