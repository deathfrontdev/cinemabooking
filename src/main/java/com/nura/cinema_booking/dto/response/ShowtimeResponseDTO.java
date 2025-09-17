package com.nura.cinema_booking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowtimeResponseDTO {
    private Long id;
    private Long movieId;
    private Long theaterId;
    private LocalDate showDate;
    private LocalTime showTime;
    private Integer totalSeats;
    private Integer availableSeats;
    private Double price;
}
