package ru.otus.base.spring.security.service;

import org.springframework.stereotype.Service;
import ru.otus.base.spring.security.domain.Author;
import ru.otus.base.spring.security.repositories.AuthorRepository;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }
}
