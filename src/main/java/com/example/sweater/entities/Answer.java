package com.example.sweater.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JoinColumn
    private Page page;
    @ManyToOne
    @JoinColumn
    private PageGame pageGame;

    private String answers;

    private boolean isFound;

    public Answer() {
    }

    public Answer(Page page, String answers) {
        this.page = page;
        this.answers = answers;
        this.isFound = false;
    }

    public Answer(PageGame pageGame, String answers) {
        this.pageGame = pageGame;
        this.answers = answers;
        this.isFound = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return id == answer.id &&
                Objects.equals(page, answer.page) &&
                Objects.equals(pageGame, answer.pageGame) &&
                Objects.equals(answers, answer.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public PageGame getPageGame() {
        return pageGame;
    }

    public void setPageGame(PageGame pageGame) {
        this.pageGame = pageGame;
    }

    public boolean isFound() {
        return isFound;
    }

    public void setFound(boolean found) {
        isFound = found;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }
}
