package ru.otus.spring.mvc.dto.convertor;

import ru.otus.spring.mvc.domain.Book;
import ru.otus.spring.mvc.dto.BookDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BookConvertor {

    private BookConvertor() {
    }

    public static Book toBook(BookDto dto) {
        Book book = new Book();
        book.setId(dto.getId());
        book.setBookName(dto.getBookName());
        book.setGenre(GenreConvertor.toGenre(dto.getGenre()));
        book.setAuthors(dto.getAuthors().stream().map(AuthorConvertor::toAuthor)
                .collect(Collectors.toList()));
        return book;
    }

    public static BookDto toBookDto(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .bookName(book.getBookName())
                .genre(GenreConvertor.toGenreDto(book.getGenre()))
                .authors(book.getAuthors().stream().map(AuthorConvertor::toAuthorDto).collect(Collectors.toList()))
                .build();
    }

    public static List<BookDto> toListBookDto(List<Book> books) {
        List<BookDto> dtoList = new ArrayList<>();
        for (Book book: books) {
            dtoList.add(toBookDto(book));
        }
        return dtoList;
    }

}
