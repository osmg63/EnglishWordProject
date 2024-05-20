package com.example.english.controller;

import com.example.english.entity.User;
import com.example.english.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/api")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/create")
    public ResponseEntity<User> add(@RequestBody User user){
        userService.save(user);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");
        User user = userService.checkCredentials(username, password);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    @GetMapping("/user/point/{id}")
    public String pointShow(@PathVariable int id) {
        return userService.pointShow(id);
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable int id) {
        User user = userService.getUserById(id);
        return user;
    }
}
