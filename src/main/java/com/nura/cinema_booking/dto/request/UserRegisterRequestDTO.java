package com.nura.cinema_booking.dto.request;

import lombok.Data;

@Data
public class UserRegisterRequestDTO {
    private String userName;
    private String email;
    private String password;
}
