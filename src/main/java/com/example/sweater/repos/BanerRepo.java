package com.example.sweater.repos;

import com.example.sweater.domain.Baner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BanerRepo extends JpaRepository<Baner, Long> {
    Optional<Baner> findById (Long id);
}
