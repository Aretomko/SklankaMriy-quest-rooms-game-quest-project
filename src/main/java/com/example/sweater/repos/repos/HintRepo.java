package com.example.sweater.repos.repos;

import com.example.sweater.entities.Hint;
import com.example.sweater.entities.Page;
import com.example.sweater.entities.PageGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HintRepo extends JpaRepository<Hint, Long> {
    @Query(value="select hint from Hint hint where hint.page = :page")
    List<Hint> FindByPage (@Param("page") Page page);
    @Query(value="select hint from Hint hint where hint.pageGame = :pageGame")
    List<Hint> FindByPageGame (@Param("pageGame") PageGame pageGame);
}
