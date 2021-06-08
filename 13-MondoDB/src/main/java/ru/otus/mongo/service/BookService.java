package ru.otus.mongo.service;

public interface BookService {

    String getAllBooks();
    String getBookById(String id);
    String getBookByName(String bookName);
    String addBook(String txt);
    String updateBookById(String id);
    String deleteBook(String id);
    String addAuthorByBookId(String id);
    String deleteAuthorByBookId(String id);
}
