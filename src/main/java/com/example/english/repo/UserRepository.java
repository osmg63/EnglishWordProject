package com.example.english.repo;

import com.example.english.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    void deleteById(int id);

    User getUserByUsername(String username);
    Optional<User> findByUsername(String username);
    Optional<User> findById(int id);

    User save(User user);
}
