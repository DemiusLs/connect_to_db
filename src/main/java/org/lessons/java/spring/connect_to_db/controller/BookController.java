
package org.lessons.java.spring.connect_to_db.controller;

import java.util.List;

import org.lessons.java.spring.connect_to_db.model.Book;
import org.lessons.java.spring.connect_to_db.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository repo;


    @GetMapping("/index")
    public String index( Model model ){
        List<Book> books = repo.findAll(); //SELECT * FROM books => lista di oggetti di tipo Book
        model.addAttribute("books" , books);
        return "/books/index";
    }

    @GetMapping("/{id}")
    public String show (@PathVariable("id") Integer id, Model model){
        Book book = repo.findById(id).get();
        model.addAttribute("book", book);

        return "/books/show";
    }

    @GetMapping("/searchByTitle")
    public String searchByTitle(@RequestParam(name ="title") String title, Model model){

        List<Book> books = repo.findByTitleContaining(title);
        model.addAttribute("books", books);
        return "/books/index";
    }
    
    @GetMapping("/searchByTitleOrAuthor")
    public String searchByTitleOrAuthor(@RequestParam(name ="query") String query, Model model){

        List<Book> books = repo.findByTitleContainingOrAuthorContaining(query , query);
        model.addAttribute("books", books);
        return "/books/index";
    }


    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("book", new Book());
        return "/books/create";

    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("book") Book formBook , BindingResult bindingResult, Model model) {
        
        if(bindingResult.hasErrors()){
            return "/books/create";
        }

        System.out.println(formBook);

        repo.save(formBook);
        

        return "redirect:/books/index";
    }
    
}