package dev.oleksa.sportshop.service;

import dev.oleksa.sportshop.model.dto.ReviewDto;
import dev.oleksa.sportshop.model.review.Review;


public interface ReviewService {
    Review createReview(ReviewDto review);

    Review readReview(Long reviewId);

    Review updateReview(ReviewDto newReview, Long oldReviewId);

    void deleteReview(Long reviewId);
}
