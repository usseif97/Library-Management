package com.library.springbootlibraryManagment.borrow;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/borrow")
public class BorrowController {
    private final BorrowService borrowService;

    @Autowired
    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @GetMapping
    public List<Borrow> getBorrows() {
        return borrowService.getBorrows();
    }

    @GetMapping(path = "{borrowId}")
    public Borrow getBookByID(@PathVariable("borrowId") Long borrowId) {
        return borrowService.getBorrowById(borrowId);
    }

    @PostMapping(path = "{bookId}/patron/{patronId}")
    public Borrow borrowBook(
        @PathVariable("bookId") Long bookId, 
        @PathVariable("patronId") Long patronId, 
        @RequestBody Date returnDate
    ) {
        return borrowService.addBorrow(bookId, patronId, returnDate);
    }

    @DeleteMapping(path = "{borrowId}")
    public Borrow deleteBorrow(@PathVariable("borrowId") Long borrowId) {
        return borrowService.deleteBorrow(borrowId);
    }

    @PutMapping(path = "{bookId}/patron/{patronId}")
    public Borrow updateBorrow(        
        @PathVariable("bookId") Long bookId, 
        @PathVariable("patronId") Long patronId
    ) {
        return borrowService.returnBorrow(bookId, patronId);
    }
}
