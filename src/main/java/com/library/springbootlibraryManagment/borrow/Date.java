package com.library.springbootlibraryManagment.borrow;

import java.time.LocalDate;


public class Date {

    private LocalDate date;

    public Date(){}

    public Date (LocalDate date){
        this.date = date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }
    
}
