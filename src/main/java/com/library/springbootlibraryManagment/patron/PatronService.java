package com.library.springbootlibraryManagment.patron;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class PatronService {

    private final PatronRepository patronRepository;

    @Autowired
    public PatronService(@Qualifier("patron") PatronRepository patronRepository) {
        this.patronRepository = patronRepository;
    }

    public List<Patron> getPatrons() {
        return patronRepository.findAll();
    }

    public Patron getPatronById(Long patronId) {
        Patron patron = patronRepository.findById(patronId)
                .orElseThrow(() -> new IllegalStateException("Patron with id " + patronId + " doesn't exist"));
        return patron;
    }

    public Patron addNewPatron(Patron patron) {
        if (patronRepository.findPatronByEmail(patron.getEmail()).isPresent()) {
            throw new IllegalStateException(patron.getEmail() + " patron is arleady exist");
        }
        patronRepository.save(patron);
        return patron;
    }

    public Patron deletePatron(Long patronId) {
        Patron patron = patronRepository.findById(patronId)
                .orElseThrow(() -> new IllegalStateException("Patron with id " + patronId + " doesn't exist"));
        patronRepository.deleteById(patronId);
        return patron;
    }

    @Transactional
    public Patron updatePatron(Long patronId,
            String name,
            String email,
            String address,
            String phone,
            LocalDate dob) {
        Patron patron = patronRepository.findById(patronId)
                .orElseThrow(() -> new IllegalStateException("Patron with id " + patronId + " doesn't exist"));

        if (name != null && name.length() > 0 && !Objects.equals(name, patron.getName())) {
            patron.setName(name);
        }

        if (email != null && email.length() > 0 && !Objects.equals(email, patron.getEmail())) {
            Optional<Patron> patronOptional = patronRepository.findPatronByEmail(email);
            if (patronOptional.isPresent()) {
                throw new IllegalStateException(email + " email is arleady exist");
            }
            patron.setEmail(email);
        }

        if (address != null && address.length() > 0 && !Objects.equals(address, patron.getAddress())) {
            patron.setAddress(address);
        }

        if (phone != null && phone.length() > 0 && !Objects.equals(phone, patron.getPhone())) {
            patron.setPhone(phone);
        }

        if (dob != null && !dob.isAfter(LocalDate.now())
                && !Objects.equals(dob, patron.getDob())) {
            patron.setDob(dob);
        }
        return patron;
    }
}
