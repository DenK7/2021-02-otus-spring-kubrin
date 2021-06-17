package ru.otus.spring.batch.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.batch.domain.h2.H2Author;
import ru.otus.spring.batch.domain.mongo.MongoAuthor;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Override
    public H2Author mapAuthor(MongoAuthor mongoAuthor) {
        return H2Author.builder()
                .authorName(mongoAuthor.getAuthorName())
                .mongoId(mongoAuthor.getId())
                .build();
    }
}
