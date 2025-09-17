package com.nura.cinema_booking.dto.request;

import com.nura.cinema_booking.enums.ReservationStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ReservationRequestDTO {
    private Long userId;
    private Long showtimeId;
    private List<Long> seatIds;
    private LocalDateTime reservationTime;
    private ReservationStatus status;
    private boolean paid;
    private BigDecimal totalPrice;
}
