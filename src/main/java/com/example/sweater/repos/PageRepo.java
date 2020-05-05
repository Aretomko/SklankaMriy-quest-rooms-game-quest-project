package com.example.sweater.repos;

import com.example.sweater.domain.Page;
import com.example.sweater.domain.Quest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PageRepo extends JpaRepository<Page, Long> {
    @Query(value="select page from Page page where page.quest = :quest")
    List<Page> findByQuest (@Param("quest") Quest quest);

    Optional<Page> findById (Long id);
}
