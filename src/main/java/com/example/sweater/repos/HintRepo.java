package com.example.sweater.repos;

import com.example.sweater.domain.Element;
import com.example.sweater.domain.Hint;
import com.example.sweater.domain.Page;
import com.example.sweater.domain.PageGame;
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
