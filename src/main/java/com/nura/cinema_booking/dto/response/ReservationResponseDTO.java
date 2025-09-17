package com.nura.cinema_booking.dto.response;

import com.nura.cinema_booking.enums.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationResponseDTO {
    private Long id;
    private Long userId;
    private String userName;
    private Long showtimeId;
    private LocalDateTime reservationTime;
    private ReservationStatus status;
    private boolean paid;
    private BigDecimal totalPrice;
    private List<Long> seatIds;
}
