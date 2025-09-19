package com.nura.cinema_booking.service;

import com.nura.cinema_booking.dto.request.ShowtimeRequestDTO;
import com.nura.cinema_booking.dto.response.ShowtimeResponseDTO;

import java.time.LocalDate;
import java.util.List;

public interface ShowtimeService {
    List<ShowtimeResponseDTO> getShowtimesByDate(LocalDate date);
    List<ShowtimeResponseDTO> getShowtimesByMovie(Long movieId);
    List<ShowtimeResponseDTO> getShowtimesByTheater(Long theaterId);
    List<ShowtimeResponseDTO> getAvailableShowtimes();

    ShowtimeResponseDTO addShowtime(ShowtimeRequestDTO dto);
    ShowtimeResponseDTO updateShowtime(Long id, ShowtimeRequestDTO dto);
    void deleteShowtime(Long id);
}
