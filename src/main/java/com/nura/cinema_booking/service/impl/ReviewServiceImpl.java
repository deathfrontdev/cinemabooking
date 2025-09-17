package com.nura.cinema_booking.service.impl;

import com.nura.cinema_booking.dto.request.ReviewRequestDTO;
import com.nura.cinema_booking.dto.response.ReviewResponseDTO;
import com.nura.cinema_booking.mapper.ReviewMapper;
import com.nura.cinema_booking.model.Movie;
import com.nura.cinema_booking.model.Review;
import com.nura.cinema_booking.model.User;
import com.nura.cinema_booking.repository.MovieRepository;
import com.nura.cinema_booking.repository.ReviewRepository;
import com.nura.cinema_booking.repository.UserRepository;
import com.nura.cinema_booking.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;
    private final ReviewMapper reviewMapper;

    public ReviewServiceImpl(ReviewRepository reviewRepository,
                             MovieRepository movieRepository,
                             UserRepository userRepository,
                             ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
        this.reviewMapper = reviewMapper;
    }

    @Override
    public ReviewResponseDTO addReview(Long movieId, ReviewRequestDTO request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        Review review = reviewMapper.toEntity(request);
        review.setUser(user);
        review.setMovie(movie);

        return reviewMapper.toDTO(reviewRepository.save(review));
    }

    @Override
    public List<ReviewResponseDTO> getUserReviews(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getReviews().stream()
                .map(reviewMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReviewResponseDTO updateReview(Long reviewId, ReviewRequestDTO request, Long userId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        if (!review.getUser().getId().equals(userId)) {
            throw new RuntimeException("You can only update your own reviews");
        }

        reviewMapper.updateEntityFromDTO(request, review);
        return reviewMapper.toDTO(reviewRepository.save(review));
    }

    @Override
    public void deleteReview(Long reviewId, Long userId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        if (!review.getUser().getId().equals(userId)) {
            throw new RuntimeException("You can only delete your own reviews");
        }

        reviewRepository.delete(review);
    }
}
