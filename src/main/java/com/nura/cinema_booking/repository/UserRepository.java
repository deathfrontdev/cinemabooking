package com.nura.cinema_booking.repository;

import com.nura.cinema_booking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    @Override
    Optional<User> findById(Long aLong);
    Optional<User> findByUserName(String userName);
}
