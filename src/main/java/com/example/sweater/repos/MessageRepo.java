package com.example.sweater.repos;

import com.example.sweater.domain.Message;
import com.example.sweater.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepo extends JpaRepository<Message, Long> {
    @Query(value="select message from Message message where message.team = :team")
    List<Message> FindByTeam (@Param("team") Team team);
}
