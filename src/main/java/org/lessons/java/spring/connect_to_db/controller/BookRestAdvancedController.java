package org.lessons.java.spring.connect_to_db.controller;

import java.util.List;
import java.util.Optional;

import org.lessons.java.spring.connect_to_db.model.Book;
import org.lessons.java.spring.connect_to_db.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api2/books")
public class BookRestAdvancedController {
    

    @Autowired
    private BookService service;

    @GetMapping
    public List<Book> index(){

        List<Book> books = service.findAll();
        return books;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> show(@PathVariable Integer id){       
        Optional<Book> bookAttempt = service.findById(id);

        if(bookAttempt.isEmpty()){
            return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Book>(bookAttempt.get(), HttpStatus.OK) ;
    }


    @PostMapping("/create")
    public ResponseEntity<Book> store(@RequestBody Book book){
        
        return new ResponseEntity<Book>(service.create(book), HttpStatus.OK) ;
    }
    

    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@RequestBody Book book, @PathVariable Integer id){
        
         if(service.findById(id).isEmpty()){
            return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
        }
        book.setId(id);
        return new ResponseEntity<Book>(service.update(book), HttpStatus.OK) ;      
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book>delete(@PathVariable Integer id){
        if(service.findById(id).isEmpty()){
            return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
        }

        service.deleteById(id);
        return new ResponseEntity<Book>(HttpStatus.OK);
    }
}
