package com.nura.cinema_booking.service.impl;

import com.nura.cinema_booking.dto.response.MovieResponseDTO;
import com.nura.cinema_booking.mapper.MovieMapper;
import com.nura.cinema_booking.model.Movie;
import com.nura.cinema_booking.repository.MovieRepository;
import com.nura.cinema_booking.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final MovieMapper mapper;

    public MovieServiceImpl(MovieRepository movieRepository, MovieMapper mapper) {
        this.movieRepository = movieRepository;
        this.mapper = mapper;
    }

    @Override
    public List<MovieResponseDTO> getAllMovies() {
        return mapper.toResponseDTOList(movieRepository.findAll());
    }

    @Override
    public MovieResponseDTO getMovieById(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        return mapper.toResponseDTO(movie);
    }

    @Override
    public MovieResponseDTO addMovie(Movie movie) {
        Movie savedMovie = movieRepository.save(movie);
        return mapper.toResponseDTO(savedMovie);
    }

    @Override
    public MovieResponseDTO updateMovie(Long id, Movie updatedMovie) {
        Movie existingMovie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        existingMovie.setTitle(updatedMovie.getTitle());
        existingMovie.setGenre(updatedMovie.getGenre());
        existingMovie.setReleaseYear(updatedMovie.getReleaseYear());
        existingMovie.setDescription(updatedMovie.getDescription());
        existingMovie.setPosterImageUrl(updatedMovie.getPosterImageUrl());

        Movie savedMovie = movieRepository.save(existingMovie);
        return mapper.toResponseDTO(savedMovie);
    }

    @Override
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
}
