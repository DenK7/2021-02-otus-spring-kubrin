package ru.otus.jdbc.service;

import org.springframework.stereotype.Service;
import ru.otus.jdbc.dao.AuthorDAO;
import ru.otus.jdbc.domain.Author;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDAO authorDAO;

    public AuthorServiceImpl(AuthorDAO authorDAO) {
        this.authorDAO = authorDAO;
    }

    @Override
    public String getAllAuthors() {
        List<Author> authorList = authorDAO.getAllAuthors();
        StringBuilder authors = new StringBuilder();
        for (Author author: authorList) {
            if (authors.length() > 0 ) {
                authors.append("; ");
            }
            authors.append("id = ").append(author.getId()).append(" name = ").append(author.getAuthorName());
        }
        return authors.toString();
    }

    @Override
    public String addAuthor(String authorName) {
        if (authorName == null || authorName.trim().length() < 1) {
            return "Author name is empty";
        }
        authorDAO.addAuthor(authorName);
        return "Author added";
    }
}
