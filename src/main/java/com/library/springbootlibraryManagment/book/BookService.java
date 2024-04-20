package com.library.springbootlibraryManagment.book;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(@Qualifier("book") BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooks() {
        log.info("Request for getBooks");
        return bookRepository.findAll();
    }

    public Book getBookByID(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalStateException("Book with id " + bookId + " doesn't exist"));
        return book;   
    }

    public Book addNewBook(Book book) {
        if (bookRepository.findBookByTitle(book.getTitle()).isPresent()) {
            throw new IllegalStateException(book.getTitle() + " book is arleady exist");
        }
        if (bookRepository.findBookByISBN(book.getIsbn()).isPresent()) {
            throw new IllegalStateException(book.getIsbn() + " ISBN is arleady exist");
        }
        bookRepository.save(book);
        return book;
    }

    public Book deleteBook(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalStateException("Book with id " + bookId + " doesn't exist"));
        bookRepository.deleteById(bookId);
        return book;
    }

    @Transactional
    public Book updateBook(Long bookId,
            String title,
            String isbn,
            String author,
            LocalDate publicationYear) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalStateException("Book with id " + bookId + " doesn't exist"));

        if (title != null && title.length() > 0 && !Objects.equals(title, book.getTitle())) {
            Optional<Book> bookOptional = bookRepository.findBookByTitle(title);
            if (bookOptional.isPresent()) {
                throw new IllegalStateException(title + " book is arleady exist");
            }
            book.setTitle(title);
        }

        if (isbn != null && isbn.length() > 0 && !Objects.equals(isbn, book.getIsbn())) {
            Optional<Book> bookOptional = bookRepository.findBookByISBN(isbn);
            if (bookOptional.isPresent()) {
                throw new IllegalStateException(isbn + " ISBN is arleady exist");
            }
            book.setISBN(isbn);
        }

        if (author != null && author.length() > 0 && !Objects.equals(author, book.getAuthor())) {
            book.setAuthor(author);
        }

        if (publicationYear != null && !publicationYear.isAfter(LocalDate.now())
                && !Objects.equals(publicationYear, book.getPublicationYear())) {
            book.setPublicationYear(publicationYear);
        }
        return book;
    }

}
