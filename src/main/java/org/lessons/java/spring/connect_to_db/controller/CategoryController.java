package org.lessons.java.spring.connect_to_db.controller;

import java.util.List;

import org.lessons.java.spring.connect_to_db.model.Book;
import org.lessons.java.spring.connect_to_db.model.Category;
import org.lessons.java.spring.connect_to_db.repository.CategoryRepository;
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
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepo;
    
    @GetMapping
    public String index(Model model){

        List<Category> categories = categoryRepo.findAll();
        model.addAttribute("categories", categories);
        return "categories/index";
    }


    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model){
        model.addAttribute("category" , categoryRepo.findById(id).get());
        return "categories/show";
    }

    @GetMapping("/create")
    public String create(Model model ){
        model.addAttribute("category" , new Category());        
        return "/categories/create-or-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("category") Category categoryForm, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            return "/categories/create-or-edit";
        }
        categoryRepo.save(categoryForm);
        return "redirect:/categories";
    }
    

    


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id ,Model model ){
        model.addAttribute("category" , categoryRepo.findById(id).get());
        model.addAttribute("edit", true);
        return "/categories/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("category") Category categoryForm, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            return "/categories/create-or-edit";
        }

        categoryRepo.save(categoryForm);
        return "redirect:/categories";
    }


    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){

        Category categoryToRemove = categoryRepo.findById(id).get();

        for(Book linkedBook : categoryToRemove.getBooks()){
            linkedBook.getCategories().remove(categoryToRemove);
        }

        categoryRepo.delete(categoryToRemove);
        return "redirect:/categories";
    }
}
