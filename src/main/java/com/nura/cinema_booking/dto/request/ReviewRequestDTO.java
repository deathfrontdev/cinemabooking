package com.nura.cinema_booking.dto.request;

import com.nura.cinema_booking.enums.ReviewStatus;
import lombok.Data;

@Data
public class ReviewRequestDTO {
    private Long userId;
    private Long movieId;
    private String comment;
    private int rating;
    private String helpfulTags;
    private ReviewStatus status;
}
