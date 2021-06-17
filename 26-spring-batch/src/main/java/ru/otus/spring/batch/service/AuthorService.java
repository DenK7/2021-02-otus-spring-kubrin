package ru.otus.spring.batch.service;

import ru.otus.spring.batch.domain.h2.H2Author;
import ru.otus.spring.batch.domain.mongo.MongoAuthor;

public interface AuthorService {

    H2Author mapAuthor (MongoAuthor mongoAuthor);
}
