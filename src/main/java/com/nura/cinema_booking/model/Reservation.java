package com.nura.cinema_booking.model;

import com.nura.cinema_booking.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "showtime_id", nullable = false)
    private Showtime showtime;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    private List<Seat> seats = new ArrayList<>();

    @Column(name = "reservation_time", nullable = false)
    private LocalDateTime reservationTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservationStatus status = ReservationStatus.CONFIRMED;

    @Column(nullable = false)
    private boolean paid = false;

    @Column(name = "total_price", nullable = false)
    private Double totalPrice;
}
