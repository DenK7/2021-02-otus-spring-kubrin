package ru.otus.jpa.service;

import org.springframework.stereotype.Service;
import ru.otus.jpa.domain.Author;
import ru.otus.jpa.repositories.AuthorRepository;

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
        Optional<Author> authorOptional = authorRepository.findById(id);
        return authorOptional
                .map(genre -> toString())
                .orElse("Author not found");
    }
}
