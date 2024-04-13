package com.assetcircle.assetmanagement.security.controller;

import com.assetcircle.assetmanagement.security.config.CustomUserDetails;
import com.assetcircle.assetmanagement.security.dto.JWTAuthResponse;
import com.assetcircle.assetmanagement.security.dto.LoginDto;
import com.assetcircle.assetmanagement.security.dto.RegisterDto;
import com.assetcircle.assetmanagement.security.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = { "/login", "/signin" })
    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDto loginDto) {
        String token = authService.login(loginDto);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String email = userDetails.getEmailId();

        JWTAuthResponse jwtAuthResponse = JWTAuthResponse.builder()
                .accessToken(token)
                .tokenType("Bearer")
                .username(username)
                .email(email)
                .build();

        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }

    @PostMapping(value = {
            "/register",
            "/signup"
    })
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        String register = authService.register(registerDto);
        return new ResponseEntity<>(register,HttpStatus.CREATED);
    }
}
