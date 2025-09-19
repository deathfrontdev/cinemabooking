package com.nura.cinema_booking.mapper;

import com.nura.cinema_booking.dto.request.ShowtimeRequestDTO;
import com.nura.cinema_booking.dto.response.ShowtimeResponseDTO;
import com.nura.cinema_booking.model.Showtime;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ShowtimeMapper {

    Showtime toEntity(ShowtimeRequestDTO dto);

    ShowtimeResponseDTO toDTO(Showtime showtime);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(ShowtimeRequestDTO dto, @MappingTarget Showtime showtime);
}
