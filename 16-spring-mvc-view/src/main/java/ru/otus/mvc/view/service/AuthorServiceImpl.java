package ru.otus.mvc.view.service;

import org.springframework.stereotype.Service;
import ru.otus.mvc.view.domain.Author;
import ru.otus.mvc.view.repositories.AuthorRepository;

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
    public String getAuthorById(String id) {
        Optional<Author> authorOptional = authorRepository.findById(id);
        if (authorOptional.isPresent()) {
            return authorOptional.get().toString();
        }
        return "Author not found";
    }

    @Override
    public String getAuthorByName(String authorName) {
        Author author = authorRepository.findAuthorByAuthorName(authorName);
        if (author != null) {
            return author.toString();
        }
        return "Author not found";
    }
}
