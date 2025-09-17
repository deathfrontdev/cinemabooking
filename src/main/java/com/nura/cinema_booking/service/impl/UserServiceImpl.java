package com.nura.cinema_booking.service.impl;

import com.nura.cinema_booking.dto.request.UserLoginRequestDTO;
import com.nura.cinema_booking.dto.request.UserRegisterRequestDTO;
import com.nura.cinema_booking.dto.response.UserResponseDTO;
import com.nura.cinema_booking.enums.Role;
import com.nura.cinema_booking.mapper.UserMapper;
import com.nura.cinema_booking.model.User;
import com.nura.cinema_booking.repository.UserRepository;
import com.nura.cinema_booking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponseDTO register(UserRegisterRequestDTO dto) {
        User user = userMapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(Role.ROLE_USER);
        user.setUserName(dto.getUserName());
        user = userRepository.save(user);
        return userMapper.toResponseDTO(user);
    }

    @Override
    public UserResponseDTO login(UserLoginRequestDTO dto) {
        User dbUser = userRepository.findByUserName(dto.getUserName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(dto.getPassword(), dbUser.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return userMapper.toResponseDTO(dbUser);
    }
}
