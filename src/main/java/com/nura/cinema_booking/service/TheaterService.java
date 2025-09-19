package com.nura.cinema_booking.service;

import com.nura.cinema_booking.dto.request.TheaterRequestDTO;
import com.nura.cinema_booking.dto.response.TheaterResponseDTO;

import java.util.List;

public interface TheaterService {
    List<TheaterResponseDTO> getAllTheaters();
    TheaterResponseDTO getTheaterById(Long id);
    List<TheaterResponseDTO> searchTheatersByLocation(String location);
    TheaterResponseDTO addTheater(TheaterRequestDTO request);
    TheaterResponseDTO updateTheater(Long id, TheaterRequestDTO request);
    void deleteTheater(Long id);
}
