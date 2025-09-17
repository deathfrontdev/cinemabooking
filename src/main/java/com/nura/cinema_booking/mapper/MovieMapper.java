package com.nura.cinema_booking.mapper;

import com.nura.cinema_booking.dto.response.MovieResponseDTO;
import com.nura.cinema_booking.dto.request.MovieRequestDTO;
import com.nura.cinema_booking.model.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    Movie toEntity(MovieRequestDTO dto);

    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "showtimes", ignore = true)
    MovieResponseDTO toResponseDTO(Movie movie);

    List<MovieResponseDTO> toResponseDTOList(List<Movie> movies);
}
