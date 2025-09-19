package com.nura.cinema_booking.mapper;

import com.nura.cinema_booking.dto.request.ReviewRequestDTO;
import com.nura.cinema_booking.dto.response.ReviewResponseDTO;
import com.nura.cinema_booking.model.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "movie.id", target = "movieId")
    ReviewResponseDTO toDTO(Review review);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)   // будем сетить в сервисе
    @Mapping(target = "movie", ignore = true)  // будем сетить в сервисе
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "upvotes", ignore = true)
    @Mapping(target = "downvotes", ignore = true)
    Review toEntity(ReviewRequestDTO request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "movie", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "upvotes", ignore = true)
    @Mapping(target = "downvotes", ignore = true)
    void updateEntityFromDTO(ReviewRequestDTO request, @MappingTarget Review review);
}
