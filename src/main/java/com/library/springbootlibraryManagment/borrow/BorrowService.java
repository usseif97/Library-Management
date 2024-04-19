package com.library.springbootlibraryManagment.borrow;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.library.springbootlibraryManagment.book.Book;
import com.library.springbootlibraryManagment.book.BookRepository;
import com.library.springbootlibraryManagment.patron.Patron;
import com.library.springbootlibraryManagment.patron.PatronRepository;

import jakarta.transaction.Transactional;

@Service
public class BorrowService {

    private final BorrowRepository borrowRepository;
    private final BookRepository bookRepository;
    private final PatronRepository patronRepository;

    @Autowired
    public BorrowService(
            @Qualifier("borrow") BorrowRepository borrowRepository,
            @Qualifier("book") BookRepository bookRepository,
            @Qualifier("patron") PatronRepository patronRepository
            ) {
        this.borrowRepository = borrowRepository;
        this.bookRepository = bookRepository;
        this.patronRepository = patronRepository;
    }

    public List<Borrow> getBorrows() {
        return borrowRepository.findAll();
    }

    public Borrow getBorrowById(Long borrowId) {
        Borrow borrow = borrowRepository.findById(borrowId)
                .orElseThrow(() -> new IllegalStateException("Borrow with id " + borrowId + " doesn't exist"));
        return borrow;
    }

    public Borrow addBorrow(Long bookId,
            Long patronId,
            Date returnDate) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalStateException("Book with id " + bookId + " doesn't exist"));
        Patron patron = patronRepository.findById(patronId)
                .orElseThrow(() -> new IllegalStateException("Patron with id " + patronId + " doesn't exist"));

        if (borrowRepository.findBorrowByBookEqualsAndPatronEquals(bookId, patronId).isPresent()) {
            throw new IllegalStateException(patron.getName() + " has arleady borrowed " + book.getTitle());
        }

        Borrow borrow = new Borrow(patron, book, returnDate.getDate());
        borrowRepository.save(borrow);
        return borrow;
    }

    public Borrow deleteBorrow(Long borrowId) {
        Borrow borrow = borrowRepository.findById(borrowId)
                .orElseThrow(() -> new IllegalStateException("Borrow with id " + borrowId + " doesn't exist"));
        borrowRepository.deleteById(borrowId);
        return borrow;
    }

    @Transactional
    public Borrow returnBorrow(Long bookId, Long patronId) {
        Borrow borrow = borrowRepository.findBorrowByBookEqualsAndPatronEquals(bookId, patronId)
                .orElseThrow(() -> new IllegalStateException(
                        "Borrow with BookId " + bookId + " & PatronId " + patronId + " doesn't exist"));

        if (borrow.getIsReturned() == true) {
            throw new IllegalStateException("Book has arleady returned back");
        }

        borrow.setReturnDate(null);
        borrow.setIsReturned(true);
        return borrow;
    }

}
