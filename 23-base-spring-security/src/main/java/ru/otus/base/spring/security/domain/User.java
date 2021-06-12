package ru.otus.base.spring.security.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(value = "user")
@Builder
public class User {
    @Id
    private String id;

    @Field(name = "user_name")
    private String userName;

    @Field(name = "password")
    private String password;

    @Field(name = "role")
    private String role;
}
