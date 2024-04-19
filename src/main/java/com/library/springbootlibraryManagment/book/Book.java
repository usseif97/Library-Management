package com.library.springbootlibraryManagment.book;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.library.springbootlibraryManagment.borrow.Borrow;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity(name = "Book")
@Table(
    name = "book",
    uniqueConstraints = {
        @UniqueConstraint(name = "book_isbn_unique", columnNames = "isbn")        
    }
)
public class Book {
    @Id
    @SequenceGenerator(
        name = "book_sequence",
        sequenceName = "book_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "book_sequence"
    )
    @Column(
        name = "id",
        updatable = false
    )
    private Long id;

    @Column(
        name = "title",
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String title;

    @Column(
        name = "isbn",
        nullable = false,
        length = 16
    )
    private String isbn;

    @Column(
        name = "author",
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String author;

    @Column(
        name = "publication_year",
        nullable = false
    )
    private LocalDate publicationYear;

    @OneToMany(
        mappedBy = "book",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    private List<Borrow> borrows = new ArrayList<>();

    public Book() {}

    public Book(Long id,
            String title,
            String isbn,
            String author,
            LocalDate publicationYear) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.publicationYear = publicationYear;
    }

    public Book(
            String title,
            String isbn,
            String author,
            LocalDate publicationYear) {
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.publicationYear = publicationYear;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setISBN(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(LocalDate publicationYear) {
        this.publicationYear = publicationYear;
    }

    @Override
    public String toString() {
        return "Book{ " + "id= " + id + "title= " + title + ", isbn= " + isbn + ", author= " + author + ", publicationYear= "
                + publicationYear + " }";
    }

}
