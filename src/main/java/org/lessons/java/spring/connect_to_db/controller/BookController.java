
package org.lessons.java.spring.connect_to_db.controller;

import java.util.List;


import org.lessons.java.spring.connect_to_db.model.Book;
import org.lessons.java.spring.connect_to_db.model.Borrowing;

import org.lessons.java.spring.connect_to_db.service.BookService;
import org.lessons.java.spring.connect_to_db.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
    private BookService bookService;
    @Autowired
    private CategoryService categoryService;


    @GetMapping("/index")
    public String index(Authentication authentication, Model model ){
        List<Book> books = bookService.findAll(); //SELECT * FROM books => lista di oggetti di tipo Book
        model.addAttribute("books" , books);
        model.addAttribute("username",authentication.getName());
        return "/books/index";
    }

    @GetMapping("/{id}")
    public String show (@PathVariable("id") Integer id, Model model){
        Book book = bookService.getById(id);
        model.addAttribute("book", book);

        return "/books/show";
    }

    @GetMapping("/searchByTitle")
    public String searchByTitle(@RequestParam(name ="title") String title, Model model){

        List<Book> books = bookService.findByTitle(title);
        model.addAttribute("books", books);
        return "/books/index";
    }
    
    @GetMapping("/searchByTitleOrAuthor")
    public String searchByTitleOrAuthor(@RequestParam(name ="query") String query, Model model){

        List<Book> books = bookService.findByTitleOrAuthor(query);
        model.addAttribute("books", books);
        return "/books/index";
    }


    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryService.findAll());
        return "/books/create-or-edit";

    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("book") Book formBook , BindingResult bindingResult, Model model) {
        
        if(bindingResult.hasErrors()){
            model.addAttribute("categories", categoryService.findAll());
            return "/books/create-or-edit";
        }

        bookService.create(formBook);
        

        return "redirect:/books/index";
    }
    

    @GetMapping("/edit/{id}")
    public String edit( @PathVariable("id") Integer id , Model model){
        model.addAttribute("book", bookService.getById(id));
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("edit" , true);
        return "/books/create-or-edit";

    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("book") Book formBook , BindingResult bindingResult, Model model) {
        
        if(bindingResult.hasErrors()){
            model.addAttribute("categories", categoryService.findAll());
            return "/books/create-or-edit";
        }

         bookService.update(formBook);
        

        return "redirect:/books/index";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        
        bookService.deleteById(id);
        
        return "redirect:/books/index";
    }
    

    @GetMapping("/{id}/borrow")
    public String borrow(@PathVariable Integer id, Model model){

        Borrowing borrowing = new Borrowing();
        borrowing.setBook(bookService.getById(id));
        model.addAttribute("borrowing", borrowing);
        return "borrowings/create-or-edit";
    }

}