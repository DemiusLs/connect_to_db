
package org.lessons.java.spring.connect_to_db.controller;

import java.util.List;

import org.lessons.java.spring.connect_to_db.model.Book;
import org.lessons.java.spring.connect_to_db.model.Borrowing;
import org.lessons.java.spring.connect_to_db.repository.BookRepository;
import org.lessons.java.spring.connect_to_db.repository.CategoryRepository;
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
    private BookRepository bookRepo;
    @Autowired
    private CategoryRepository categoryRepo;


    @GetMapping("/index")
    public String index( Model model ){
        List<Book> books = bookRepo.findAll(); //SELECT * FROM books => lista di oggetti di tipo Book
        model.addAttribute("books" , books);
        return "/books/index";
    }

    @GetMapping("/{id}")
    public String show (@PathVariable("id") Integer id, Model model){
        Book book = bookRepo.findById(id).get();
        model.addAttribute("book", book);

        return "/books/show";
    }

    @GetMapping("/searchByTitle")
    public String searchByTitle(@RequestParam(name ="title") String title, Model model){

        List<Book> books = bookRepo.findByTitleContaining(title);
        model.addAttribute("books", books);
        return "/books/index";
    }
    
    @GetMapping("/searchByTitleOrAuthor")
    public String searchByTitleOrAuthor(@RequestParam(name ="query") String query, Model model){

        List<Book> books = bookRepo.findByTitleContainingOrAuthorContaining(query , query);
        model.addAttribute("books", books);
        return "/books/index";
    }


    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryRepo.findAll());
        return "/books/create-or-edit";

    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("book") Book formBook , BindingResult bindingResult, Model model) {
        
        if(bindingResult.hasErrors()){
            model.addAttribute("categories", categoryRepo.findAll());
            return "/books/create-or-edit";
        }

        bookRepo.save(formBook);
        

        return "redirect:/books/index";
    }
    

    @GetMapping("/edit/{id}")
    public String edit( @PathVariable("id") Integer id , Model model){
        model.addAttribute("book", bookRepo.findById(id).get());
        model.addAttribute("categories", categoryRepo.findAll());
        model.addAttribute("edit" , true);
        return "/books/create-or-edit";

    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("book") Book formBook , BindingResult bindingResult, Model model) {
        
        if(bindingResult.hasErrors()){
            model.addAttribute("categories", categoryRepo.findAll());
            return "/books/create-or-edit";
        }

        bookRepo.save(formBook);
        

        return "redirect:/books/index";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        
        bookRepo.deleteById(id);
        
        return "redirect:/books/index";
    }
    

    @GetMapping("/{id}/borrow")
    public String borrow(@PathVariable Integer id, Model model){

        Borrowing borrowing = new Borrowing();
        borrowing.setBook(bookRepo.findById(id).get());
        model.addAttribute("borrowing", borrowing);
        return "borrowings/create-or-edit";
    }

}