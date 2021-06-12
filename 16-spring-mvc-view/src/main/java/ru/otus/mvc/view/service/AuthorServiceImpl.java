package ru.otus.mvc.view.service;

import org.springframework.stereotype.Service;
import ru.otus.mvc.view.domain.Author;
import ru.otus.mvc.view.repositories.AuthorRepository;

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
