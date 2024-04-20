package com.library.springbootlibraryManagment;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.library.springbootlibraryManagment.book.Book;
import com.library.springbootlibraryManagment.book.BookController;
import com.library.springbootlibraryManagment.book.BookService;

@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest {
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new ParameterNamesModule())
            .registerModule(new Jdk8Module())
            .registerModule(new JavaTimeModule()); // new module

    ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    Book RECORD_1 = new Book(1L, "Jane Eyre", "B59Cr562oP0Q9xC9", "Charlotte Brontë",
            LocalDate.of(1847, Month.JANUARY, 5));
    Book RECORD_2 = new Book(2L, "The Great Gatsby", "B59Rr562oP0Q9xC9", "F. Scott Fitzgerald",
            LocalDate.of(1925, Month.FEBRUARY, 5));
    Book RECORD_3 = new Book(3L, "Pride and Prejudice", "B59CV562oP0Q9xC9", " Jane Austen,",
            LocalDate.of(1813, Month.MARCH, 19));
    Book RECORD_4 = new Book(4L, "Little Women", "B59Cr562bU0Q9xC9", "Louisa May Alcott,",
            LocalDate.of(1869, Month.NOVEMBER, 5));

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        bookController = new BookController(bookService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    public void getBooks_sucess() throws Exception {
        List<Book> books = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3, RECORD_4));
        Mockito.when(bookService.getBooks()).thenReturn(books);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/v1/book")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[2].title", is("Pride and Prejudice")))
                .andExpect(jsonPath("$[3].title", is("Little Women")));

    }

    @Test
    public void getBookById_sucess() throws Exception {
        Mockito.when(bookService.getBookByID(RECORD_1.getId())).thenReturn(RECORD_1);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/v1/book/" + RECORD_1.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.title", is("Jane Eyre")));
    }

    @Test
    public void addBook_sucess() throws Exception {
        Book newRecord = Book.builder()
                .id(5L)
                .title("Frankenstein")
                .isbn("Z69Cr562oP0Q9xC9")
                .author("Mary Shelley")
                .publicationYear(LocalDate.of(1818, Month.OCTOBER, 14))
                .build();
        Mockito.when(bookService.addNewBook(newRecord)).thenReturn(newRecord);
        String content = objectMapper.writeValueAsString(newRecord);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/v1/book")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());
    }

    @Test
    public void updateBook_sucess() throws Exception {
        Book updatedRecord = Book.builder()
                .id(1L)
                .title("updated name")
                .isbn("Z69Cr562oP0Q9xC9")
                .author("Mary Shelley")
                .publicationYear(LocalDate.of(1818, Month.OCTOBER, 14))
                .build();

        Mockito.when(bookService.updateBook(1L, 
            "updated title", 
            "B59Cr562oP0Q9xC9",
            "Mary Shelley",
            LocalDate.of(1818, Month.OCTOBER, 14)))
        .thenReturn(updatedRecord);
                
        String content = objectMapper.writeValueAsString(updatedRecord);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/api/v1/book/" + updatedRecord.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content);
        
        mockMvc.perform(mockRequest)
                        .andExpect(status().isOk());
    }

    @Test
    public void deleteBook_sucess() throws Exception {
        Mockito.when(bookService.deleteBook(RECORD_2.getId())).thenReturn(RECORD_2);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .delete("/api/v1/book/" + RECORD_2.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.title", is("The Great Gatsby")));
    }

}
