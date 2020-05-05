package com.example.sweater.repos;

import com.example.sweater.domain.Game;
import com.example.sweater.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepo extends JpaRepository<Game, Long> {

}
