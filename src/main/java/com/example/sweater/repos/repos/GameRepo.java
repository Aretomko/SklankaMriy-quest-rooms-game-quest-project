package com.example.sweater.repos.repos;

import com.example.sweater.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepo extends JpaRepository<Game, Long> {

}
