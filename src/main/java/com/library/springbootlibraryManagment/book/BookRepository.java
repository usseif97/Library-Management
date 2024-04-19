package com.library.springbootlibraryManagment.book;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Qualifier("book")
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT s FROM Book s WHERE s.title = :title")
    Optional<Book> findBookByTitle(@Param("title") String title);

    @Query("SELECT s FROM Book s WHERE s.isbn = :isbn")
    Optional<Book> findBookByISBN(@Param("isbn") String isbn);
    
}
