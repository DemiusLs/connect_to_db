package org.lessons.java.spring.connect_to_db.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

@Entity
@Table(name = "borrowings")
public class Borrowing {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //libro da cui dipendo
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @NotNull(message = "Borrowing date cannot be null")
    @PastOrPresent(message = "Borrowing date cannot be in the future")  
    private LocalDate borrowingDate;
    @PastOrPresent(message = "Return date cannot be in the future")
    private LocalDate returnDate;

    @Lob
    private String notes;

     public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return this.book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getBorrowingDate() {
        return this.borrowingDate;
    }

    public void setBorrowingDate(LocalDate borrowingDate) {
        this.borrowingDate = borrowingDate;
    }

    public LocalDate getReturnDate() {
        return this.returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString(){

        return String.format("%s - %s",borrowingDate,borrowingDate);
    }
}

   
