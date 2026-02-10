package com.placement.portal.controller;

import com.placement.portal.dto.auth.AuthResponse;
import com.placement.portal.dto.auth.LoginRequest;
import com.placement.portal.dto.auth.LoginResponse;
import com.placement.portal.dto.auth.RegisterRequest;
import com.placement.portal.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request){
        authService.register(request);
        return new ResponseEntity<> (new AuthResponse("User registered successfully"),
                HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> Login(@Valid @RequestBody LoginRequest request){

        String token = authService.login(request.getEmail(), request.getPassword());

        return  ResponseEntity.ok(new LoginResponse(token));
    }

}
