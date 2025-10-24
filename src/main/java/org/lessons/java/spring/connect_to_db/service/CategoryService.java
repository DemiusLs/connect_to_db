package org.lessons.java.spring.connect_to_db.service;

import java.util.List;

import org.lessons.java.spring.connect_to_db.model.Category;
import org.lessons.java.spring.connect_to_db.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    
    
    @Autowired
    private CategoryRepository categoryRepo;

    public List<Category> findAll(){

        return categoryRepo.findAll();
    }
    
    public Category getById(Integer id){

        return categoryRepo.findById(id).get();
    }


    public Category create(Category category){
        return categoryRepo.save(category);
    }
    
     public Category update(Category category){
       return  categoryRepo.save(category);
    }

    public void delete(Category category){
        
        categoryRepo.delete(category);
    }


    public void deleteById(Integer id){
        Category category = getById(id);
        delete(category);
    }




}
