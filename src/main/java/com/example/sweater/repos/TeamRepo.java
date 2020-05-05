package com.example.sweater.repos;

import com.example.sweater.domain.Quest;
import com.example.sweater.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TeamRepo  extends JpaRepository<Team, Long> {
    Optional<Team> findById (Long id);

    Team findByCode(String code);
    @Query(value="select team from Team team where team.quest = :quest")
    List<Team> FindByQuest (@Param("quest")Quest quest);
}
