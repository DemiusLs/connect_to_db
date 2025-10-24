package org.lessons.java.spring.connect_to_db.repository;
import org.lessons.java.spring.connect_to_db.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
    
}
