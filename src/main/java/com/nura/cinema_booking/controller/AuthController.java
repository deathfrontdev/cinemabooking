package com.nura.cinema_booking.controller;

import com.nura.cinema_booking.config.JwtUtil;
import com.nura.cinema_booking.dto.request.UserLoginRequestDTO;
import com.nura.cinema_booking.dto.request.UserRegisterRequestDTO;
import com.nura.cinema_booking.dto.response.UserResponseDTO;
import com.nura.cinema_booking.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody UserRegisterRequestDTO dto) {
        UserResponseDTO savedUser = userService.register(dto);
        String token = jwtUtil.generateToken(savedUser.getUserName());

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Пользователь успешно зарегистрирован!");
        response.put("user", savedUser);
        response.put("token", token);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody UserLoginRequestDTO dto) {
        UserResponseDTO dbUser = userService.login(dto);
        String token = jwtUtil.generateToken(dbUser.getUserName());

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Успешный вход в систему!");
        response.put("user", dbUser);
        response.put("token", token);

        return ResponseEntity.ok(response);
    }
}
