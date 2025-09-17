package com.nura.cinema_booking.service;

import com.nura.cinema_booking.dto.request.UserLoginRequestDTO;
import com.nura.cinema_booking.dto.request.UserRegisterRequestDTO;
import com.nura.cinema_booking.dto.response.UserResponseDTO;

public interface UserService {
    UserResponseDTO register(UserRegisterRequestDTO dto);
    UserResponseDTO login(UserLoginRequestDTO dto);
}
