package ru.otus.mvc.view.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.mvc.view.domain.Book;
import ru.otus.mvc.view.service.AuthorServiceImpl;
import ru.otus.mvc.view.service.BookServiceImpl;
import ru.otus.mvc.view.service.GenreServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
//@ComponentScan(value = "ru.otus.mvc.view.repositories")
@DisplayName("Контроллер для работы с Book должен ")
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MongoTemplate template;
    @MockBean
    private BookServiceImpl bookService;
    @MockBean
    private AuthorServiceImpl authorService;
    @MockBean
    private GenreServiceImpl genreService;

//    @Test
//    @DisplayName(" возвращать нужное количество строк")
//    public void listBooksTest() throws Exception {
//        Book book = new Book();
//        book.setId("1");
//        book.setBookName("test");
//        List<Book> books = new ArrayList<>();
//        books.add(book);
//        given(bookService.getAllBooks()).willReturn(books);
//        this.mockMvc.perform(get("/"))
//                .andExpect(status().isOk())
//                .andExpect(content().string("test"));
//    }
}
