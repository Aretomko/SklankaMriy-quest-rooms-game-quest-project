package com.example.sweater.repos.repos;

import com.example.sweater.entities.Quest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuestRepo extends JpaRepository<Quest, Long> {
    List<Quest> findAll();

    Optional<Quest> findById (Long id);



}
