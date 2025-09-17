package com.nura.cinema_booking.dto.request;
import lombok.Data;

@Data
public class UserLoginRequestDTO {
    private String userName;
    private String password;
}
