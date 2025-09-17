package com.nura.cinema_booking.dto.request;

import lombok.Data;

@Data
public class SeatRequestDTO {
    private Long showtimeId;
    private String seatNumber;
    private Boolean isReserved;
    private Long reservationId;
}
