package com.nura.cinema_booking.controller;

import com.nura.cinema_booking.dto.request.ShowtimeRequestDTO;
import com.nura.cinema_booking.dto.response.ShowtimeResponseDTO;
import com.nura.cinema_booking.service.ShowtimeService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/showtimes")
public class ShowtimeController {

    private final ShowtimeService showtimeService;

    public ShowtimeController(ShowtimeService showtimeService) {
        this.showtimeService = showtimeService;
    }

    @GetMapping
    public List<ShowtimeResponseDTO> getShowtimesByDate(@RequestParam LocalDate date) {
        return showtimeService.getShowtimesByDate(date);
    }

    @GetMapping("/movies/{movieId}")
    public List<ShowtimeResponseDTO> getShowtimesByMovie(@PathVariable Long movieId) {
        return showtimeService.getShowtimesByMovie(movieId);
    }

    @GetMapping("/theaters/{theaterId}")
    public List<ShowtimeResponseDTO> getShowtimesByTheater(@PathVariable Long theaterId) {
        return showtimeService.getShowtimesByTheater(theaterId);
    }

    @GetMapping("/available")
    public List<ShowtimeResponseDTO> getAvailableShowtimes() {
        return showtimeService.getAvailableShowtimes();
    }

    @PostMapping
    public ShowtimeResponseDTO addShowtime(@RequestBody ShowtimeRequestDTO dto) {
        return showtimeService.addShowtime(dto);
    }

    @PutMapping("/{id}")
    public ShowtimeResponseDTO updateShowtime(@PathVariable Long id,
                                              @RequestBody ShowtimeRequestDTO dto) {
        return showtimeService.updateShowtime(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteShowtime(@PathVariable Long id) {
        showtimeService.deleteShowtime(id);
    }
}
