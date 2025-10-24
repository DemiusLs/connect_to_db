package org.lessons.java.spring.connect_to_db.repository;
import java.util.List;
import org.lessons.java.spring.connect_to_db.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer>{
    
    public List<Book> findByTitleContaining(String title);
    public List<Book> findByTitleContainingOrAuthorContaining(String title, String author);
}
