package com.example.sweater.repos.repos;

import com.example.sweater.entities.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicationService extends JpaRepository<Application, Long> {
    Optional<Application> findById (Long id);
}
