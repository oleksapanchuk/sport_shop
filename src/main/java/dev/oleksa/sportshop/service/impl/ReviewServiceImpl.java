package dev.oleksa.sportshop.service.impl;

import dev.oleksa.sportshop.model.dto.ReviewDto;
import dev.oleksa.sportshop.model.review.Review;
import dev.oleksa.sportshop.repository.ProductItemRepository;
import dev.oleksa.sportshop.repository.ReviewRepository;
import dev.oleksa.sportshop.repository.UserRepository;
import dev.oleksa.sportshop.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ProductItemRepository productItemRepository;

    @Override
    public Review createReview(ReviewDto review) {
        return Review.builder()
                .comment(review.getComment())
                .ratingValue(review.getRatingValue())
                .user(userRepository.findById(review.getUserId()).orElseThrow())
                .product(productItemRepository.findById(review.getUserId()).orElseThrow())
                .build();
    }

    @Override
    public Review readReview(Long reviewId) {
        return reviewRepository.findById(reviewId).orElseThrow();
    }

    @Override
    public Review updateReview(ReviewDto newReview, Long oldReviewId) {
        Review review = reviewRepository.findById(oldReviewId).orElseThrow();
        review.setComment(newReview.getComment());
        review.setRatingValue(newReview.getRatingValue());
        review.setUser(userRepository.findById(newReview.getUserId()).orElseThrow());
        review.setProduct(productItemRepository.findById(newReview.getUserId()).orElseThrow());
        return null;
    }

    @Override
    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}