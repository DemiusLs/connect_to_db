package org.lessons.java.spring.connect_to_db.service;

import java.util.List;
import java.util.Optional;

import org.lessons.java.spring.connect_to_db.model.Book;
import org.lessons.java.spring.connect_to_db.model.Borrowing;
import org.lessons.java.spring.connect_to_db.repository.BookRepository;
import org.lessons.java.spring.connect_to_db.repository.BorrowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    
    @Autowired
    private BookRepository bookRepo;
    @Autowired
    private BorrowingRepository borrowingRepo;

    public List<Book> findAll(){

        return bookRepo.findAll();
    }

    public Optional<Book> findById(Integer id){
        return bookRepo.findById(id);
    }
    
    public Book getById(Integer id){

        return bookRepo.findById(id).get();
    }

    public List<Book> findByTitle(String title){
        return bookRepo.findByTitleContaining(title);
    }


    public List<Book> findByTitleOrAuthor(String query){

        return bookRepo.findByTitleContainingOrAuthorContaining(query , query);
    }

    public Book create(Book book){
        return bookRepo.save(book);
    }
    
     public Book update(Book book){
       return  bookRepo.save(book);
    }

    public void delete(Book book){
        for(Borrowing borrowingToDelete : book.getBorrowings()){
            borrowingRepo.delete(borrowingToDelete);
        }
        bookRepo.delete(book);
    }


    public void deleteById(Integer id){
        Book book = getById(id);
        delete(book);
    }




}
