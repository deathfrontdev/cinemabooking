package com.nura.cinema_booking.service;

import com.nura.cinema_booking.dto.response.MovieResponseDTO;
import com.nura.cinema_booking.model.Movie;
import java.util.List;

public interface MovieService {
    List<MovieResponseDTO> getAllMovies();

    MovieResponseDTO getMovieById(Long id);

    MovieResponseDTO addMovie(Movie movie);

    MovieResponseDTO updateMovie(Long id, Movie updatedMovie);

    void deleteMovie(Long id);
}
