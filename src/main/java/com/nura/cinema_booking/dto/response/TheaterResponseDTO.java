package com.nura.cinema_booking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TheaterResponseDTO {
    private Long id;
    private String name;
    private String location;
    private Integer capacity;
}
