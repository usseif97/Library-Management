package com.library.springbootlibraryManagment.patron;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Qualifier("patron")
@Repository
public interface PatronRepository extends JpaRepository<Patron, Long> {

    @Query("SELECT s FROM Patron s WHERE s.email = :email")
    Optional<Patron> findPatronByEmail(@Param("email") String email);
    
}
