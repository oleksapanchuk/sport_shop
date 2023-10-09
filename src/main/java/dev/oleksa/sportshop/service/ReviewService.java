package dev.oleksa.sportshop.service;

import dev.oleksa.sportshop.dto.ReviewDto;


public interface ReviewService {
    ReviewDto createReview(ReviewDto reviewDto);

    ReviewDto readReview(Long reviewId);

    ReviewDto updateReview(ReviewDto reviewDto);

    Boolean deleteReview(Long reviewId);
}
