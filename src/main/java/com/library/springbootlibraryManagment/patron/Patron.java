package com.library.springbootlibraryManagment.patron;

import java.time.LocalDate;
import java.time.Period;
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
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;

@Entity(name = "Patron")
@Table(
    name = "patron",
    uniqueConstraints = {
        @UniqueConstraint(name = "patron_email_unique", columnNames = "email")        
    }
)
public class Patron {
    @Id
    @SequenceGenerator(
        name = "patron_sequence", 
        sequenceName = "patron_sequence", 
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE, 
        generator = "patron_sequence"
    )
    @Column(
        name = "id",
        updatable = false
    )
    private Long id;
    
    @Column(
        name = "name",
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String name;

    @Column(
        name = "email",
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String email;

    @Column(
        name = "address",
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String address;

    @Column(
        name = "phone",
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String phone;

    @Column(
        name = "dob",
        nullable = false
    )
    private LocalDate dob;

    @Transient
    private Integer age;

    @OneToMany(
        mappedBy = "patron",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    private List<Borrow> borrows = new ArrayList<>();

    public Patron() {
    }

    public Patron(Long id,
            String name,
            String email,
            String address,
            String phone,
            LocalDate dob) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.dob = dob;
    }

    public Patron(
            String name,
            String email,
            String address,
            String phone,
            LocalDate dob) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.dob = dob;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getAge() {
        return Period.between(dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Patron{ " + "id= " + id + "name= " + name + "email= " + email + ", address= " + address + ", phone= " + phone + ", dob= "
                + dob + ", age= " + age + " }";
    }

}
