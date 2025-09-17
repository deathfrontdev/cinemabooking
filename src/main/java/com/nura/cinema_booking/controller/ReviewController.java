package com.nura.cinema_booking.controller;

import com.nura.cinema_booking.dto.request.ReviewRequestDTO;
import com.nura.cinema_booking.dto.response.ReviewResponseDTO;
import com.nura.cinema_booking.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/movies/{movieId}")
    public ResponseEntity<ReviewResponseDTO> addReview(
            @PathVariable Long movieId,
            @RequestBody ReviewRequestDTO request) {
        return ResponseEntity.ok(reviewService.addReview(movieId, request));
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<ReviewResponseDTO>> getUserReviews(@PathVariable Long userId) {
        return ResponseEntity.ok(reviewService.getUserReviews(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponseDTO> updateReview(
            @PathVariable Long id,
            @RequestBody ReviewRequestDTO request) {
        return ResponseEntity.ok(reviewService.updateReview(id, request, request.getUserId()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(
            @PathVariable Long id,
            @RequestBody ReviewRequestDTO request) {
        reviewService.deleteReview(id, request.getUserId());
        return ResponseEntity.noContent().build();
    }
}
