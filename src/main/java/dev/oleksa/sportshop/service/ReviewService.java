package dev.oleksa.sportshop.service;

import dev.oleksa.sportshop.model.dto.ReviewDto;
import dev.oleksa.sportshop.model.review.Review;


public interface ReviewService {
    ReviewDto createReview(ReviewDto reviewDto);

    ReviewDto readReview(Long reviewId);

    ReviewDto updateReview(ReviewDto reviewDto);

    Boolean deleteReview(Long reviewId);
}
