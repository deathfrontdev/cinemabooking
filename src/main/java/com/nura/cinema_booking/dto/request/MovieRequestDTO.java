package com.nura.cinema_booking.dto.request;

import lombok.Data;

@Data
public class MovieRequestDTO {
    private String title;
    private String genre;
    private int releaseYear;
    private String description;
    private String posterImageUrl;
}
