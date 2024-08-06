package com.example.demo.repository;

import com.example.demo.model.Review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	   List<Review> findByBookId(Long bookId);
	    void deleteByIdAndBookId(Long id, Long bookId);
}