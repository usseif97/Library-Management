package com.library.springbootlibraryManagment.borrow;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.library.springbootlibraryManagment.book.Book;
import com.library.springbootlibraryManagment.patron.Patron;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity(name = "Borrow")
@Table(name = "borrow", uniqueConstraints = {
        @UniqueConstraint(
            name = "borrow_patron_with_book_unique",
            columnNames = { "patron_id", "book_id" }
        )
    }
)
public class Borrow {
    @Id
    @SequenceGenerator(
        name = "borrow_sequence", 
        sequenceName = "borrow_sequence", 
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE, 
        generator = "borrow_sequence"
    )
    @Column(
        name = "id", 
        updatable = false
    )
    private Long id;

    @ManyToOne
    @JoinColumn(
        name = "patron_id", 
        nullable = false, 
        referencedColumnName = "id", 
        foreignKey = @ForeignKey(name = "patron_fk")
    )
    private Patron patron;

    @ManyToOne()
    @JoinColumn(
        name = "book_id", 
        nullable = false, 
        referencedColumnName = "id", 
        foreignKey = @ForeignKey(name = "book_fk")
    )
    private Book book;

    @Column(
        name = "is_returned", 
        nullable = false
    )
    private boolean isReturned = false;

    @Column(
        name = "return_date",
        nullable= true
    )
    private LocalDate returnDate;

    public Borrow() {
    }

    public Borrow(Long id,
            Patron patron,
            Book book,
            LocalDate returnDate) {
        this.id = id;
        this.patron = patron;
        this.book = book;
        this.returnDate = returnDate;
    }

    public Borrow(Patron patron,
            Book book,
            LocalDate returnDate) {
        this.patron = patron;
        this.book = book;
        this.returnDate = returnDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patron getPatron() {
        return patron;
    }

    public void setPatron(Patron patron) {
        this.patron = patron;
    }

    public Book getBook() {
        return book;
    }

    public void setId(Book book) {
        this.book = book;
    }

    public boolean getIsReturned() {
        return isReturned;
    }

    public void setIsReturned(boolean isReturned) {
        this.isReturned = isReturned;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "Borrpw{ " + "id= " + id + "patron= " + patron + "book= " + book + ", isReturned= " + isReturned
                + ", returnDate= " + returnDate + " }";
    }

}
