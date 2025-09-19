package com.nura.cinema_booking.service.impl;

import com.nura.cinema_booking.dto.request.ShowtimeRequestDTO;
import com.nura.cinema_booking.dto.response.ShowtimeResponseDTO;
import com.nura.cinema_booking.mapper.ShowtimeMapper;
import com.nura.cinema_booking.model.Movie;
import com.nura.cinema_booking.model.Showtime;
import com.nura.cinema_booking.model.Theater;
import com.nura.cinema_booking.repository.MovieRepository;
import com.nura.cinema_booking.repository.ShowtimeRepository;
import com.nura.cinema_booking.repository.TheaterRepository;
import com.nura.cinema_booking.service.ShowtimeService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowtimeServiceImpl implements ShowtimeService {

    private final ShowtimeRepository showtimeRepository;
    private final MovieRepository movieRepository;
    private final TheaterRepository theaterRepository;
    private final ShowtimeMapper showtimeMapper;

    public ShowtimeServiceImpl(ShowtimeRepository showtimeRepository,
                               MovieRepository movieRepository,
                               TheaterRepository theaterRepository,
                               ShowtimeMapper showtimeMapper) {
        this.showtimeRepository = showtimeRepository;
        this.movieRepository = movieRepository;
        this.theaterRepository = theaterRepository;
        this.showtimeMapper = showtimeMapper;
    }

    @Override
    public List<ShowtimeResponseDTO> getShowtimesByDate(LocalDate date) {
        return showtimeRepository.findByShowDate(date)
                .stream()
                .map(showtimeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ShowtimeResponseDTO> getShowtimesByMovie(Long movieId) {
        return showtimeRepository.findByMovie_Id(movieId)
                .stream()
                .map(showtimeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ShowtimeResponseDTO> getShowtimesByTheater(Long theaterId) {
        return showtimeRepository.findByTheater_Id(theaterId)
                .stream()
                .map(showtimeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ShowtimeResponseDTO> getAvailableShowtimes() {
        return showtimeRepository.findByAvailableSeatsGreaterThan(0)
                .stream()
                .map(showtimeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ShowtimeResponseDTO addShowtime(ShowtimeRequestDTO dto) {
        Movie movie = movieRepository.findById(dto.getMovieId())
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        Theater theater = theaterRepository.findById(dto.getTheaterId())
                .orElseThrow(() -> new RuntimeException("Theater not found"));

        Showtime showtime = showtimeMapper.toEntity(dto);
        showtime.setMovie(movie);
        showtime.setTheater(theater);

        return showtimeMapper.toDTO(showtimeRepository.save(showtime));
    }

    @Override
    public ShowtimeResponseDTO updateShowtime(Long id, ShowtimeRequestDTO dto) {
        Showtime showtime = showtimeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Showtime not found"));

        showtimeMapper.updateEntityFromDTO(dto, showtime);

        if (dto.getMovieId() != null) {
            Movie movie = movieRepository.findById(dto.getMovieId())
                    .orElseThrow(() -> new RuntimeException("Movie not found"));
            showtime.setMovie(movie);
        }

        if (dto.getTheaterId() != null) {
            Theater theater = theaterRepository.findById(dto.getTheaterId())
                    .orElseThrow(() -> new RuntimeException("Theater not found"));
            showtime.setTheater(theater);
        }

        return showtimeMapper.toDTO(showtimeRepository.save(showtime));
    }

    @Override
    public void deleteShowtime(Long id) {
        if (!showtimeRepository.existsById(id)) {
            throw new RuntimeException("Showtime not found");
        }
        showtimeRepository.deleteById(id);
    }
}
