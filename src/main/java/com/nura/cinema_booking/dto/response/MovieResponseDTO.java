package com.nura.cinema_booking.dto.response;

import com.nura.cinema_booking.dto.response.ReviewResponseDTO;
import com.nura.cinema_booking.dto.response.ShowtimeResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponseDTO {
    private Long id;
    private String title;
    private String genre;
    private int releaseYear;
    private String description;
    private String posterImageUrl;
    private List<ReviewResponseDTO> reviews;
    private List<ShowtimeResponseDTO> showtimes;
}
