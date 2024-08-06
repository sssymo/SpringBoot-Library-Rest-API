package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Review;
import com.example.demo.repository.ReviewRepository;

@Service
public class ReviewService {
 @Autowired
private ReviewRepository reviewRepository;
@Autowired
private BookService bs;
public Review createReview(Long bookId, Review review) {
    review.setBook(bs.getBookById(bookId));
    return reviewRepository.save(review);
}

public List<Review> getReviewsByBookId(Long bookId) {
    return reviewRepository.findByBookId(bookId);
}

public void deleteReview(Long bookId, Long reviewId) {
    reviewRepository.deleteByIdAndBookId(reviewId, bookId);
}}

