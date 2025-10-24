package org.lessons.java.spring.connect_to_db.service;

import java.util.List;

import org.lessons.java.spring.connect_to_db.model.Borrowing;
import org.lessons.java.spring.connect_to_db.repository.BorrowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BorrowingService {
    
    
    @Autowired
    private BorrowingRepository borrowingRepo;

    public List<Borrowing> findAll(){

        return borrowingRepo.findAll();
    }
    
    public Borrowing getById(Integer id){

        return borrowingRepo.findById(id).get();
    }


    public Borrowing create(Borrowing borrowing){
        return borrowingRepo.save(borrowing);
    }
    
     public Borrowing update(Borrowing borrowing){
       return  borrowingRepo.save(borrowing);
    }

    public void delete(Borrowing borrowing){
        
        borrowingRepo.delete(borrowing);
    }


    public void deleteById(Integer id){
        Borrowing borrowing = getById(id);
        delete(borrowing);
    }




}
