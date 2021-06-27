package ru.otus.spring.mvc.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.mvc.dto.AuthorDto;
import ru.otus.spring.mvc.dto.convertor.AuthorConvertor;
import ru.otus.spring.mvc.repositories.AuthorRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<AuthorDto> getAllAuthors() {
        return authorRepository.findAll().stream().map(AuthorConvertor::toAuthorDto).collect(Collectors.toList());
    }
}
