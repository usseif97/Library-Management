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
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.library.springbootlibraryManagment.book.Book;
import com.library.springbootlibraryManagment.borrow.Borrow;
import com.library.springbootlibraryManagment.borrow.BorrowController;
import com.library.springbootlibraryManagment.borrow.BorrowService;
import com.library.springbootlibraryManagment.borrow.Date;
import com.library.springbootlibraryManagment.patron.Patron;
import com.fasterxml.jackson.databind.ObjectWriter;

@RunWith(MockitoJUnitRunner.class)
public class BorrowControllerTest {
        private MockMvc mockMvc;

        ObjectMapper objectMapper = new ObjectMapper()
                        .registerModule(new ParameterNamesModule())
                        .registerModule(new Jdk8Module())
                        .registerModule(new JavaTimeModule()); // new module

        ObjectWriter objectWriter = objectMapper.writer();

        @Mock
        private BorrowService borrowService;

        @InjectMocks
        private BorrowController borrowController;

        Book allMySons = new Book(
                        "All My Sons",
                        "A59Cr562oP0Q9xC9",
                        "Arthur Miller",
                        LocalDate.of(1970, Month.JANUARY, 5));

        Book hamlet = new Book(
                        "Hamlet",
                        "A60Xv497oR6W26QZ",
                        "William Shakespeare",
                        LocalDate.of(1960, Month.JANUARY, 5));

        Book journeyToCenterOfEarth = new Book(
                        "journeyToCenterOfEarth",
                        "A60Xv497oR9W26QZ",
                        "William Shakespeare",
                        LocalDate.of(1960, Month.JANUARY, 5));

        Patron usseif = new Patron(
                        "Usseif",
                        "usseif@gmail.com",
                        "Madinet Nasr - Egypt",
                        "+201116135358",
                        LocalDate.of(1997, Month.JANUARY, 5));

        Patron eslam = new Patron(
                        "Eslam",
                        "eslam@gmail.com",
                        "Maadi - Egypt",
                        "+201003675965",
                        LocalDate.of(2000, Month.JANUARY, 5));

        Borrow RECORD_1 = new Borrow(usseif, allMySons, LocalDate.of(2024, Month.JANUARY, 5));
        Borrow RECORD_2 = new Borrow(usseif, hamlet, LocalDate.of(2024, Month.FEBRUARY, 5));
        Borrow RECORD_3 = new Borrow(eslam, allMySons, LocalDate.of(2024, Month.MARCH, 5));
        Borrow RECORD_4 = new Borrow(eslam, hamlet, LocalDate.of(2024, Month.APRIL, 5));

        @Before
        public void setUp() {
                MockitoAnnotations.initMocks(this);
                borrowController = new BorrowController(borrowService);
                this.mockMvc = MockMvcBuilders.standaloneSetup(borrowController).build();
        }

        @Test
        public void getBorrows_sucess() throws Exception {
                List<Borrow> borrows = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3, RECORD_4));
                Mockito.when(borrowService.getBorrows()).thenReturn(borrows);
                mockMvc.perform(
                                MockMvcRequestBuilders
                                                .get("/api/v1/borrow")
                                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk())
                                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(4)))
                                .andExpect(jsonPath("$[2].isReturned", is(false)))
                                .andExpect(jsonPath("$[3].isReturned", is(false)));

        }

        @Test
        public void makeBorrow_sucess() throws Exception {
                Book bookRecord = Book.builder()
                                .id(5L)
                                .title("Frankenstein")
                                .isbn("Z69Cr562oP0Q9xC9")
                                .author("Mary Shelley")
                                .publicationYear(LocalDate.of(1818, Month.OCTOBER, 14))
                                .build();

                Patron patronRecord = Patron.builder()
                                .id(5L)
                                .name("Mahmoud")
                                .email("mahmoud@gmail.com")
                                .address("Alex")
                                .phone("+201006932457")
                                .dob(LocalDate.of(2000, Month.OCTOBER, 14))
                                .age(24)
                                .build();

                Borrow newRecord = Borrow.builder()
                                .id(5L)
                                .patron(patronRecord)
                                .book(bookRecord)
                                .returnDate(LocalDate.of(2024, Month.OCTOBER, 14))
                                .build();

                Mockito.when(borrowService.addBorrow(bookRecord.getId(), patronRecord.getId(),
                                new Date(newRecord.getReturnDate()))).thenReturn(newRecord);

                String content = objectMapper.writeValueAsString(newRecord);

                MockHttpServletRequestBuilder mockRequest = 
                        MockMvcRequestBuilders.post("/api/v1/borrow/" + bookRecord.getId() + "/patron/" + patronRecord.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(content);

                mockMvc.perform(mockRequest)
                                .andExpect(status().isOk());
        }

        @Test
        public void returnBook_sucess() throws Exception {
                Book bookRecord = Book.builder()
                                .id(5L)
                                .title("Frankenstein")
                                .isbn("Z69Cr562oP0Q9xC9")
                                .author("Mary Shelley")
                                .publicationYear(LocalDate.of(1818, Month.OCTOBER, 14))
                                .build();

                Patron patronRecord = Patron.builder()
                                .id(5L)
                                .name("Mahmoud")
                                .email("mahmoud@gmail.com")
                                .address("Alex")
                                .phone("+201006932457")
                                .dob(LocalDate.of(2000, Month.OCTOBER, 14))
                                .age(24)
                                .build();

                Borrow newRecord = Borrow.builder()
                                .id(5L)
                                .patron(patronRecord)
                                .book(bookRecord)
                                .returnDate(LocalDate.of(2024, Month.OCTOBER, 14))
                                .build();

                Mockito.when(borrowService.returnBorrow(bookRecord.getId(), patronRecord.getId())).thenReturn(newRecord);

                String content = objectMapper.writeValueAsString(newRecord);

                MockHttpServletRequestBuilder mockRequest = 
                        MockMvcRequestBuilders.put("/api/v1/borrow/" + bookRecord.getId() + "/patron/" + patronRecord.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(content);

                mockMvc.perform(mockRequest)
                                .andExpect(status().isOk());
        }
}
