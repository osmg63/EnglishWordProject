package com.example.english.controller;

import com.example.english.dto.DtoLoginIU;
import com.example.english.dto.DtoUser;
import com.example.english.dto.DtoUserIU;

import com.example.english.service.AuthService;
import com.example.english.service.JwtService;
import com.example.english.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final AuthService authService;



    public UserController(UserService userService, JwtService jwtService, AuthenticationManager authenticationManager, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/generateToken")
    public ResponseEntity<String> generateToken(@RequestBody DtoLoginIU request) {
        String data= authService.generateToken(request);
        return ResponseEntity.ok(data);
    }

    @PostMapping("/create")
    public ResponseEntity<DtoUser> add(@RequestBody @Valid DtoUserIU user){

          var data= userService.save(user);
            return ResponseEntity.ok(data);


    }
    @PostMapping("/login")
    public ResponseEntity<DtoUser> login(@RequestBody @Valid DtoLoginIU dto) {
        DtoUser user = userService.checkCredentials(dto.getUsername(),dto.getPassword());
        return ResponseEntity.ok(user);
    }

    @GetMapping("/pointShowById/{id}")
    public String pointShow(@PathVariable int id) {
        return userService.pointShow(id);
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<DtoUser> getUserById(@PathVariable int id) {
        DtoUser dtoUser = userService.getUserById(id);
        return ResponseEntity.ok(dtoUser);
    }

}
