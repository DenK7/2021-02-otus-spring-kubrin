package ru.otus.spring.batch.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.batch.domain.h2.H2Author;
import ru.otus.spring.batch.domain.mongo.MongoAuthor;
import ru.otus.spring.batch.repositories.h2.H2AuthorRepository;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final H2AuthorRepository authorRepository;

    public AuthorServiceImpl(H2AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public H2Author mapAuthor(MongoAuthor mongoAuthor) {
        return H2Author.builder()
                .authorName(mongoAuthor.getAuthorName())
                .mongoId(mongoAuthor.getId())
                .build();
    }

    @Override
    public String getAllAuthors() {
        List<H2Author> authors = authorRepository.findAll();
        StringBuilder builder = new StringBuilder();
        for (H2Author author: authors) {
            if (builder.length() > 0) {
                builder.append("; ");
            }
            builder.append(author.toString());
        }
        return builder.toString();
    }
}
