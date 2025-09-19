package com.nura.cinema_booking.repository;

import com.nura.cinema_booking.model.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {
    List<Showtime> findByShowDate(LocalDate date);
    List<Showtime> findByMovie_Id(Long movieId);
    List<Showtime> findByTheater_Id(Long theaterId);
    List<Showtime> findByAvailableSeatsGreaterThan(int seats);
}
