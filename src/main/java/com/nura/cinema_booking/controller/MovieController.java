package com.nura.cinema_booking.controller;

import com.nura.cinema_booking.dto.request.MovieRequestDTO;
import com.nura.cinema_booking.dto.response.MovieResponseDTO;
import com.nura.cinema_booking.mapper.MovieMapper;
import com.nura.cinema_booking.model.Movie;
import com.nura.cinema_booking.service.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final MovieMapper movieMapper;

    public MovieController(MovieService movieService, MovieMapper movieMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
    }

    @GetMapping
    public List<MovieResponseDTO> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public MovieResponseDTO getMovieById(@PathVariable Long id) {
        return movieService.getMovieById(id);
    }

    @PostMapping
    public MovieResponseDTO createMovie(@RequestBody MovieRequestDTO dto) {
        Movie movie = movieMapper.toEntity(dto);
        return movieService.addMovie(movie);
    }

    @PutMapping("/{id}")
    public MovieResponseDTO updateMovie(@RequestBody MovieRequestDTO dto, @PathVariable Long id) {
        Movie movie = movieMapper.toEntity(dto);
        return movieService.updateMovie(id, movie);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
    }
}
