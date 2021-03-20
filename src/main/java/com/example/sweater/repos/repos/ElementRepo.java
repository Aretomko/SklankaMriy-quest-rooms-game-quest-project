package com.example.sweater.repos.repos;

import com.example.sweater.entities.Element;
import com.example.sweater.entities.Page;
import com.example.sweater.entities.PageGame;
import com.example.sweater.entities.Quest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ElementRepo extends JpaRepository<Element, Long> {
    @Query(value="select element from Element element where element.page = :page")
    List<Element> FindByPage (@Param("page") Page page);
    @Query(value="select element from Element element where element.pageGame = :pageGame")
    List<Element> FindByPageGame (@Param("pageGame") PageGame pageGame);
    @Query(value="select element from Element element where element.quest = :quest")
    List<Element> FindByQuest (@Param("quest") Quest quest);
    Optional<Element> findById (Long id);
}
