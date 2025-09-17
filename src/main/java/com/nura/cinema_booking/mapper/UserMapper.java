package com.nura.cinema_booking.mapper;

import com.nura.cinema_booking.dto.request.UserRegisterRequestDTO;
import com.nura.cinema_booking.dto.response.UserResponseDTO;
import com.nura.cinema_booking.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "reservations", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "role", ignore = true)
    User toEntity(UserRegisterRequestDTO dto);

    UserResponseDTO toResponseDTO(User user);
}
