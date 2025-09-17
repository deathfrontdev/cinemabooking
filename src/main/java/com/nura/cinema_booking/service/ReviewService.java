package com.nura.cinema_booking.service;

import com.nura.cinema_booking.dto.request.ReviewRequestDTO;
import com.nura.cinema_booking.dto.response.ReviewResponseDTO;

import java.util.List;

public interface ReviewService {
    ReviewResponseDTO addReview(Long movieId, ReviewRequestDTO request);
    List<ReviewResponseDTO> getUserReviews(Long userId);
    ReviewResponseDTO updateReview(Long reviewId, ReviewRequestDTO request, Long userId);
    void deleteReview(Long reviewId, Long userId);

}
