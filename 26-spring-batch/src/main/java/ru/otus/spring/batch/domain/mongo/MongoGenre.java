package ru.otus.spring.batch.domain.mongo;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(value = "genre")
@Builder
public class MongoGenre {
    @Id
    private String id;

    @Field(name = "genre_name")
    private String genreName;

    @Override
    public String toString() {
        return "id = " + id + ", genreName = " + genreName;
    }
}
