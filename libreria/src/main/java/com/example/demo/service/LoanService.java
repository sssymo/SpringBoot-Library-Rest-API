package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import com.example.demo.model.Book;
import com.example.demo.model.Loan;
import com.example.demo.model.User;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.LoanRepository;
import com.example.demo.repository.UserRepository;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    public Loan saveLoan(Loan loanRequest) {
        Book book = bookRepository.findById(loanRequest.getBook().getId())
                .orElseThrow(() -> new RuntimeException("Book not found"));
        User user = userRepository.findById(loanRequest.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Loan loan = new Loan();
        loan.setBook(book);
        loan.setUser(user);
        loan.setLoanDate((loanRequest.getLoanDate()));
        loan.setReturnDate((loanRequest.getReturnDate()));

        return loanRepository.save(loan);
    }
    public List<Loan> getLoansByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return loanRepository.findByUser(user);
    }

    public List<Loan> getLoansByBook(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        return loanRepository.findByBook(book);
    }
    public Optional<Loan> getLoan(Long id) {
        return loanRepository.findById(id);
    }

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public void deleteLoan(Long id) {
        loanRepository.deleteById(id);
    }
}