package com.nura.cinema_booking.mapper;

import com.nura.cinema_booking.dto.request.SeatRequestDTO;
import com.nura.cinema_booking.dto.response.SeatResponseDTO;
import com.nura.cinema_booking.model.Reservation;
import com.nura.cinema_booking.model.Seat;
import com.nura.cinema_booking.model.Showtime;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SeatMapper {

    @Mapping(source = "showtime.id", target = "showtimeId")
    @Mapping(source = "reservation.id", target = "reservationId")
    SeatResponseDTO toDto(Seat seat);

    List<SeatResponseDTO> toDtoList(List<Seat> seats);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "showtime", expression = "java(toShowtime(dto.getShowtimeId()))")
    @Mapping(target = "reservation", expression = "java(toReservation(dto.getReservationId()))")
    Seat toEntity(SeatRequestDTO dto);

    default Showtime toShowtime(Long id) {
        if (id == null) return null;
        Showtime s = new Showtime();
        s.setId(id);
        return s;
    }

    default Reservation toReservation(Long id) {
        if (id == null) return null;
        Reservation r = new Reservation();
        r.setId(id);
        return r;
    }
}
