package com.library.springbootlibraryManagment.patron;

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

@RestController
@RequestMapping(path = "api/v1/patron")
public class PatronController {

    private final PatronService patronService;

    @Autowired
    public PatronController(PatronService patronService) {
        this.patronService = patronService;
    }

    @GetMapping
    public List<Patron> getPatrons() {
        return patronService.getPatrons();
    }

    @GetMapping(path = "{patronId}")
    public Patron getPatronByID(@PathVariable("patronId") Long patronId) {
        return patronService.getPatronById(patronId);
    }

    @PostMapping
    public Patron addNewPatron(@RequestBody Patron patron) {
        return patronService.addNewPatron(patron);
    }

    @DeleteMapping(path = "{patronId}")
    public Patron deleteBook(@PathVariable("patronId") Long patronId) {
        return patronService.deletePatron(patronId);
    }

    @PutMapping(path = "{patronId}")
    public Patron updateBook(@PathVariable("patronId") Long patronId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) LocalDate dob) {
        return patronService.updatePatron(patronId, name, email, address, phone, dob);
    }
    
}
