package com.nura.cinema_booking.controller;

import com.nura.cinema_booking.dto.request.TheaterRequestDTO;
import com.nura.cinema_booking.dto.response.TheaterResponseDTO;
import com.nura.cinema_booking.service.TheaterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theaters")
public class TheaterController {

    private final TheaterService theaterService;

    public TheaterController(TheaterService theaterService) {
        this.theaterService = theaterService;
    }

    @GetMapping
    public ResponseEntity<List<TheaterResponseDTO>> getAllTheaters() {
        return ResponseEntity.ok(theaterService.getAllTheaters());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TheaterResponseDTO> getTheaterById(@PathVariable Long id) {
        return ResponseEntity.ok(theaterService.getTheaterById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<TheaterResponseDTO>> searchTheatersByLocation(@RequestParam String location) {
        return ResponseEntity.ok(theaterService.searchTheatersByLocation(location));
    }

    @PostMapping
    public ResponseEntity<TheaterResponseDTO> addTheater(@RequestBody TheaterRequestDTO request) {
        return ResponseEntity.ok(theaterService.addTheater(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TheaterResponseDTO> updateTheater(
            @PathVariable Long id,
            @RequestBody TheaterRequestDTO request) {
        return ResponseEntity.ok(theaterService.updateTheater(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTheater(@PathVariable Long id) {
        theaterService.deleteTheater(id);
        return ResponseEntity.noContent().build();
    }
}
