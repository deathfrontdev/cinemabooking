package com.nura.cinema_booking.repository;

import com.nura.cinema_booking.model.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TheaterRepository extends JpaRepository<Theater, Long> {
    List<Theater> findByLocationContainingIgnoreCase(String location);
}
