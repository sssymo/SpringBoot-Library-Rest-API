package com.example.demo.repository;


import com.example.demo.model.Book;
import com.example.demo.model.Loan;
import com.example.demo.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findByUser(User user);

    List<Loan> findByBook(Book book);
}