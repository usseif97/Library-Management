package com.library.springbootlibraryManagment;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.library.springbootlibraryManagment.book.Book;
import com.library.springbootlibraryManagment.book.BookRepository;
import com.library.springbootlibraryManagment.borrow.Borrow;
import com.library.springbootlibraryManagment.borrow.BorrowRepository;
import com.library.springbootlibraryManagment.patron.Patron;
import com.library.springbootlibraryManagment.patron.PatronRepository;

@Configuration
public class appConfig {
    /*@Bean
    CommandLineRunner commandLineRunner(
                    @Qualifier("book") BookRepository bookRepository,
                    @Qualifier("patron") PatronRepository patronRepository,
                    @Qualifier("borrow") BorrowRepository borrowRepository) {
            return args -> {
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

                    bookRepository.saveAll(List.of(allMySons, hamlet, journeyToCenterOfEarth));

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

                    patronRepository.saveAll(List.of(usseif, eslam));

                    Borrow usseifBorrow1 = new Borrow(usseif, allMySons, LocalDate.of(2000, Month.JANUARY, 5));
                    Borrow usseifBorrow2 = new Borrow(usseif, hamlet, LocalDate.of(2000, Month.JANUARY, 5));
                    Borrow eslamBorrow1 = new Borrow(eslam, allMySons, LocalDate.of(2000, Month.JANUARY, 5));
                    Borrow eslamBorrow2 = new Borrow(eslam, hamlet, LocalDate.of(2000, Month.JANUARY, 5));

                    borrowRepository.saveAll(List.of(usseifBorrow1, usseifBorrow2, eslamBorrow1, eslamBorrow2));
            };
    }*/
}
