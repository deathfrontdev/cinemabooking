package com.nura.cinema_booking.dto.request;

import lombok.Data;

@Data
public class TheaterRequestDTO {
    private String name;
    private String location;
    private Integer capacity;
}
