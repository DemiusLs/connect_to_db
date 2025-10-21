package org.lessons.java.spring.connect_to_db.repository;
import org.lessons.java.spring.connect_to_db.model.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowingRepository extends JpaRepository<Borrowing,Integer>{
    
}
