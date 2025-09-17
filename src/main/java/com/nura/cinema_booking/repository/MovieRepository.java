package com.nura.cinema_booking.repository;

import com.nura.cinema_booking.dto.response.MovieResponseDTO;
import com.nura.cinema_booking.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findAll();
    Movie findById(long id);
    @Override
    void deleteById(Long aLong);
}