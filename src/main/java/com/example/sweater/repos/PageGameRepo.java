package com.example.sweater.repos;

import com.example.sweater.domain.Game;
import com.example.sweater.domain.PageGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PageGameRepo  extends JpaRepository<PageGame, Long> {
    @Query(value="select pageGame from PageGame pageGame where pageGame.game = :game")
    List<PageGame> FindByGame (@Param("game") Game game);

    Optional<PageGame> findById (Long id);
}
