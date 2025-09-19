package com.nura.cinema_booking.service.impl;

import com.nura.cinema_booking.dto.request.TheaterRequestDTO;
import com.nura.cinema_booking.dto.response.TheaterResponseDTO;
import com.nura.cinema_booking.mapper.TheaterMapper;
import com.nura.cinema_booking.model.Theater;
import com.nura.cinema_booking.repository.TheaterRepository;
import com.nura.cinema_booking.service.TheaterService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TheaterServiceImpl implements TheaterService {

    private final TheaterRepository theaterRepository;
    private final TheaterMapper theaterMapper;

    public TheaterServiceImpl(TheaterRepository theaterRepository, TheaterMapper theaterMapper) {
        this.theaterRepository = theaterRepository;
        this.theaterMapper = theaterMapper;
    }

    @Override
    public List<TheaterResponseDTO> getAllTheaters() {
        return theaterRepository.findAll()
                .stream()
                .map(theaterMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TheaterResponseDTO getTheaterById(Long id) {
        Theater theater = theaterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Theater not found"));
        return theaterMapper.toDTO(theater);
    }

    @Override
    public List<TheaterResponseDTO> searchTheatersByLocation(String location) {
        return theaterRepository.findByLocationContainingIgnoreCase(location)
                .stream()
                .map(theaterMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TheaterResponseDTO addTheater(TheaterRequestDTO request) {
        Theater theater = theaterMapper.toEntity(request);
        return theaterMapper.toDTO(theaterRepository.save(theater));
    }

    @Override
    public TheaterResponseDTO updateTheater(Long id, TheaterRequestDTO request) {
        Theater theater = theaterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Theater not found"));

        theaterMapper.updateEntityFromDTO(request, theater);

        return theaterMapper.toDTO(theaterRepository.save(theater));
    }

    @Override
    public void deleteTheater(Long id) {
        Theater theater = theaterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Theater not found"));
        theaterRepository.delete(theater);
    }
}
