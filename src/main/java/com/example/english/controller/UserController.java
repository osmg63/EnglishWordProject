package com.example.english.controller;

import com.example.english.dto.DtoChangePassword;
import com.example.english.dto.DtoLoginIU;
import com.example.english.dto.DtoUser;
import com.example.english.dto.DtoUserIU;

import com.example.english.service.AuthService;
import com.example.english.service.JwtService;
import com.example.english.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    @PostMapping("/auth/google")
    public ResponseEntity<String> googleLogin(@RequestBody Map<String, String> body) {
        String idToken = body.get("idToken");
     return ResponseEntity.ok(authService.authWithGoogle(idToken));
    }
    @PostMapping("/changePassword")
    public ResponseEntity<Boolean> changePassword(@RequestBody @Valid DtoChangePassword dto)
    {
        Boolean bool=userService.changePassword(dto);
        return ResponseEntity.ok(bool);
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

    @GetMapping("/{id}/pointShow")
    public String pointShow(@PathVariable int id) {
        return userService.pointShow(id);
    }

    @GetMapping("/{userName}")
    public ResponseEntity<DtoUser> getUserByUserName(@PathVariable String userName) {
        DtoUser dtoUser = userService.getUserByUserName(userName);
        return ResponseEntity.ok(dtoUser);
    }
    @PutMapping("/{id}")
    public ResponseEntity<DtoUser> update(@PathVariable int id,@RequestBody  DtoUser dto) {
        DtoUser user = userService.updateUser(id,dto);
        return ResponseEntity.ok(user);
    }

}
