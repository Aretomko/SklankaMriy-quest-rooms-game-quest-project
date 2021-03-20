package com.example.sweater.repos.repos;

import com.example.sweater.entities.Answer;
import com.example.sweater.entities.Page;
import com.example.sweater.entities.PageGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnswerRepo extends JpaRepository<Answer, Long> {
    @Query(value="select answer from Answer answer where answer.page = :page")
    List<Answer> FindByPage (@Param("page") Page page);
    @Query(value="select answer from Answer answer where answer.pageGame = :pageGame")
    List<Answer> FindByPageGame (@Param("pageGame") PageGame pageGame);
}
