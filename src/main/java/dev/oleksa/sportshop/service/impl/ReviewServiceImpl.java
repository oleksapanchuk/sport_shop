package dev.oleksa.sportshop.service.impl;

import dev.oleksa.sportshop.mapper.ReviewMapper;
import dev.oleksa.sportshop.model.dto.ReviewDto;
import dev.oleksa.sportshop.repository.ReviewRepository;
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
    private final ReviewMapper reviewMapper;


    @Override
    public ReviewDto createReview(ReviewDto reviewDto) {
        var review = reviewMapper.toEntity(reviewDto);

        review = reviewRepository.save(review);

        return reviewMapper.toDto(review);
    }

    @Override
    public ReviewDto readReview(Long reviewId) {
        return reviewMapper.toDto(
                reviewRepository.findById(reviewId)
                        .orElseThrow()
        );
    }

    @Override
    public ReviewDto updateReview(ReviewDto reviewDto) {
        var review = reviewMapper.toEntity(reviewDto);

        review = reviewRepository.save(review);

        return reviewMapper.toDto(review);
    }

    @Override
    public Boolean deleteReview(Long reviewId) {
        try {
            reviewRepository.deleteById(reviewId);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }
}