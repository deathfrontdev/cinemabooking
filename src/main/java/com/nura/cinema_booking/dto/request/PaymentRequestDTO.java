package com.nura.cinema_booking.dto.request;

import com.nura.cinema_booking.enums.PaymentStatus;
import lombok.Data;

@Data
public class PaymentRequestDTO {
    private Long reservationId;
    private String paymentIntentId;
    private Double amount;
    private PaymentStatus status;
}
