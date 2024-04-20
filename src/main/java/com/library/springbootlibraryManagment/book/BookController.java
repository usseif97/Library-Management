package com.library.springbootlibraryManagment.book;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "api/v1/book")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @GetMapping(path = "{bookId}")
    public Book getBookByID(@PathVariable("bookId") Long bookId) {
        return bookService.getBookByID(bookId);
    }

    @PostMapping
    public Book addNewBook(@RequestBody Book book) {
        return bookService.addNewBook(book);
    }

    @DeleteMapping(path = "{bookId}")
    public Book deleteBook(@PathVariable("bookId") Long bookId) {
        return bookService.deleteBook(bookId);
    }

    @PutMapping(path = "{bookId}")
    public Book updateBook(@PathVariable("bookId") Long bookId,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String isbn,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) LocalDate publicationYear) {
        return bookService.updateBook(bookId, title, isbn, author, publicationYear);
    }

}
