package com.example.english.service;

import com.example.english.dto.DtoLoginIU;
import com.example.english.exception.BaseException;
import com.example.english.exception.ErrorMessage;
import com.example.english.exception.MessageType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }
    public String generateToken(DtoLoginIU dto) {
        try
        {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
            if (authentication.isAuthenticated()) {
                return jwtService.generateToken(dto.getUsername());
            }
            throw new BaseException(new ErrorMessage(MessageType.FORBIDDEN));

        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.TOKEN_GENERATE_FAILED,e.getMessage()));
        }
    }
}
