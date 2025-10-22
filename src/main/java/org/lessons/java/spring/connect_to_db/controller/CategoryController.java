package org.lessons.java.spring.connect_to_db.controller;

import org.lessons.java.spring.connect_to_db.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepo;
    
    @GetMapping
    public String index(Model model){
        model.addAttribute("category", categoryRepo.findAll());
        return "categories/index";
    }
}
