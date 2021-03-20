package com.example.sweater.repos.repos;

import com.example.sweater.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByActivationCode(String code);

    Optional<User> findById(Long id);
}
