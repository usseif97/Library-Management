package com.library.springbootlibraryManagment.borrow;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Qualifier("borrow")
@Repository
public interface BorrowRepository  extends JpaRepository<Borrow, Long>  {

    @Query("SELECT b FROM Borrow b WHERE b.book.id = :bookId AND b.patron.id = :patronId")
    Optional<Borrow> findBorrowByBookEqualsAndPatronEquals(@Param("bookId") Long bookId, @Param("patronId") Long patronId);
    
}
