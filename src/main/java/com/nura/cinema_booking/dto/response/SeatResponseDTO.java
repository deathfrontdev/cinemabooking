package com.nura.cinema_booking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeatResponseDTO {
    private Long id;
    private Long showtimeId;
    private String seatNumber;
    private Boolean isReserved;
    private Long reservationId;
}
