package com.example.sweater.repos.repos;

import com.example.sweater.entities.Baner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BanerRepo extends JpaRepository<Baner, Long> {
    Optional<Baner> findById (Long id);
}
