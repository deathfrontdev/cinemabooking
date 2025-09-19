package com.nura.cinema_booking.mapper;

import com.nura.cinema_booking.dto.request.TheaterRequestDTO;
import com.nura.cinema_booking.dto.response.TheaterResponseDTO;
import com.nura.cinema_booking.model.Theater;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TheaterMapper {

    Theater toEntity(TheaterRequestDTO dto);

    TheaterResponseDTO toDTO(Theater entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(TheaterRequestDTO dto, @MappingTarget Theater entity);
}
