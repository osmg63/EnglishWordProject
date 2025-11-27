package com.example.english.service;

import com.example.english.dto.DtoLoginIU;
import com.example.english.entity.Role;
import com.example.english.entity.User;
import com.example.english.exception.BaseException;
import com.example.english.exception.ErrorMessage;
import com.example.english.exception.MessageType;
import com.example.english.repo.UserRepository;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;



    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String googleClientId;

    public AuthService(AuthenticationManager authenticationManager, JwtService jwtService, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
    public String authWithGoogle(String idToken) {
        try {
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                    new NetHttpTransport(),
                    new GsonFactory()
            ).setAudience(Collections.singletonList(googleClientId))
                    .build();

            GoogleIdToken token = verifier.verify(idToken);
            if (token == null) {
                throw new RuntimeException("Invalid ID token");
            }

            GoogleIdToken.Payload payload = (GoogleIdToken.Payload) token.getPayload();
            String email = payload.getEmail();
            String name = (String) payload.get("name");

            User user= userRepository.findByUsername(email)
                    .orElseGet(()->{
                        User user1=new User();
                        user1.setUsername(email);
                        user1.setPassword(passwordEncoder.encode(email));
                        user1.setName(name);
                        user1.setAuthorities(Collections.singleton(Role.ROLE_USER));
                        user1.setAccountNonExpired(true);
                        user1.setAccountNonLocked(true);
                        user1.setCredentialsNonExpired(true);
                        user1.setEnabled(true);
                        return userRepository.save(user1);
                    });
            DtoLoginIU dtoLoginIU=new DtoLoginIU();
            dtoLoginIU.setUsername(email);
            dtoLoginIU.setPassword(email);
            return generateToken(dtoLoginIU);
        } catch (Exception e) {
            throw new RuntimeException("Google authentication failed", e);
        }
    }
}
