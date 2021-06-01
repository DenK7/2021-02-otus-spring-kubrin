package ru.otus.mvc.view.service;

import org.springframework.stereotype.Service;
import ru.otus.mvc.view.domain.Genre;
import ru.otus.mvc.view.repositories.GenreRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public String getAllGenre() {
        List<Genre> genres = genreRepository.findAll();
        StringBuilder builder = new StringBuilder();
        for (Genre genre: genres) {
            if (builder.length() > 0) {
                builder.append("; ");
            }
            builder.append(genre.toString());
        }
        return builder.toString();
    }

    @Override
    public String getGenreById(String id) {
        Optional<Genre> genreOptional = genreRepository.findById(id);
        if (genreOptional.isPresent()) {
            return genreOptional.get().toString();
        }
        return "Genre not found";
    }

    @Override
    public String getGenreByName(String genreName) {
        Genre genre = genreRepository.findGenreByGenreName(genreName);
        if (genre != null) {
            return genre.toString();
        }
        return "Genre not found";    }
}
