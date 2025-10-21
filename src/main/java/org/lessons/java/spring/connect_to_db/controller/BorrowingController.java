package org.lessons.java.spring.connect_to_db.controller;

import org.lessons.java.spring.connect_to_db.model.Borrowing;
import org.lessons.java.spring.connect_to_db.repository.BorrowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/borrowings")
public class BorrowingController {

    @Autowired
    private BorrowingRepository repository;
    
    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("borrowing") Borrowing formBorrowing,BindingResult bindingResult, Model model){

        System.out.println(formBorrowing);
        if(bindingResult.hasErrors()){
            return "borrowings/create-or-edit";
        }

        repository.save(formBorrowing);

        return "redirect:/books/" + formBorrowing.getBook().getId();

    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("borrowing", repository.findById(id).get());
        model.addAttribute("edit", true);

        return "borrowings/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("borrowing") Borrowing formBorrowing , BindingResult bindingResult, Model model) {
        
        if(bindingResult.hasErrors()){
            return "borrowings/create-or-edit";
        }

        repository.save(formBorrowing);
        

        return "redirect:/books/index";
    }
    
}
