package com.example.english.service;

import com.example.english.entity.User;
import com.example.english.repo.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public List<User> findAll(){
        return userRepository.findAll();
    }
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    public User getUserByUserName(String username) {
        return userRepository.getUserByUsername(username);
    }
    public User checkCredentials(String username, String password) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }
    public void addPoint(int id){
        User user=userRepository.findById(id).orElseThrow();
        System.out.println(user.getPoint());
        user.setPoint(user.getPoint()+5);
        user.setTestNumber(user.getTestNumber()+1);
        userRepository.save(user);
    }
    public String pointShow(int id){
        User user = userRepository.findById(id).orElseThrow();
        return String.valueOf(user.getPoint());
    }
    public Optional<User> findUser(int id) {
        return userRepository.findById(id);
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElseThrow();
    }

}
