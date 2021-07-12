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
        book.setId(dto.getId() != null ? String.valueOf(dto.getId()) : null);
        book.setBookName(dto.getBookName());
        book.setGenre(GenreConvertor.toGenre(dto.getGenre()));
        if (dto.getAuthors() != null) {
            book.setAuthors(dto.getAuthors().stream().map(AuthorConvertor::toAuthor)
                    .collect(Collectors.toList()));
        }
        return book;
    }

    public static BookDto toBookDto(Book book) {
         BookDto dto = BookDto.builder()
                .id(Long.valueOf(book.getId()))
                .bookName(book.getBookName())
                .genre(GenreConvertor.toGenreDto(book.getGenre()))
                .build();
         if (book.getAuthors() != null) {
             dto.setAuthors(book.getAuthors().stream().map(AuthorConvertor::toAuthorDto).collect(Collectors.toList()));
         }
         return dto;
    }

    public static List<BookDto> toListBookDto(List<Book> books) {
        List<BookDto> dtoList = new ArrayList<>();
        for (Book book: books) {
            dtoList.add(toBookDto(book));
        }
        return dtoList;
    }

}
