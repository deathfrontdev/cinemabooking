package com.nura.cinema_booking.dto.response;

import com.nura.cinema_booking.enums.ReviewStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseDTO {
    private Long id;
    private Long userId;
    private String userName;
    private Long movieId;
    private String movieTitle;
    private String comment;
    private int rating;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer upvotes;
    private Integer downvotes;
    private String helpfulTags;
    private ReviewStatus status;
}
