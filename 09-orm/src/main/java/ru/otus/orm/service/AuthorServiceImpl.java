package ru.otus.orm.service;

import org.springframework.stereotype.Service;
import ru.otus.orm.domain.Author;
import ru.otus.orm.repositories.AuthorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public String getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        StringBuilder builder = new StringBuilder();
        for (Author author: authors) {
            if (builder.length() > 0) {
                builder.append("; ");
            }
            builder.append(author.toString());
        }
        return builder.toString();
    }

    @Override
    public String getAuthorById(Long id) {
        return Optional.of(authorRepository.findById(id).get().toString())
                .orElse("Author not found");
    }
}
