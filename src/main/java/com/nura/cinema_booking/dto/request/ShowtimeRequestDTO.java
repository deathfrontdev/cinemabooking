package com.nura.cinema_booking.dto.request;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ShowtimeRequestDTO {
    private Long movieId;
    private Long theaterId;
    private LocalDate showDate;
    private LocalTime showTime;
    private Integer totalSeats;
    private Integer availableSeats;
    private Double price;
}
