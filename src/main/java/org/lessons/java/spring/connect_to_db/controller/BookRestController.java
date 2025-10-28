package org.lessons.java.spring.connect_to_db.controller;

import java.util.List;

import org.lessons.java.spring.connect_to_db.model.Book;
import org.lessons.java.spring.connect_to_db.service.BookService;
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
@RequestMapping("/api/books")
public class BookRestController {
    
    @Autowired
    private BookService service;

    @GetMapping
    public List<Book> index(){

        List<Book> books = service.findAll();
        return books;
    }

    @GetMapping("/{id}")
    public Book show(@PathVariable Integer id){       
        Book book = service.getById(id);
        return book;
    }


    @PostMapping("/create")
    public Book store(@RequestBody Book book){
        System.out.println(book);
        return service.create(book);
    }
    

    @PutMapping("/{id}")
    public Book update(@RequestBody Book book, @PathVariable Integer id){
        book.setId(id);
        return service.update(book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        service.deleteById(id);
    }
}