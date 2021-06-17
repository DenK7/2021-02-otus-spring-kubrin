package ru.otus.spring.batch.service;

import ru.otus.spring.batch.domain.h2.H2Book;
import ru.otus.spring.batch.domain.mongo.MongoBook;

public interface BookService {

    H2Book mapBook(MongoBook mongoBook);
    String getAllBooks();
}
