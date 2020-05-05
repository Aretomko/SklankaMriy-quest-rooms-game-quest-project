package com.example.sweater.domain;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JoinColumn
    private Quest quest;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private Team team;
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<PageGame> pageGames;

    private boolean isStarted;

    private boolean isPaused;

    private boolean isFinished;

    private Date start;

    private Date finish;

    private String sum;

    private String pause;
    //new
    private Date pauseStart;

    private Date pauseFinish;

    public Game() {
    }

    public Game(Team team, Quest quest, List<PageGame> pageGames){
        this.pageGames = pageGames;
        this.team = team;
        this.quest = quest;
        this.isStarted=false;
        this.isFinished = false;
    }
    public List<PageGame> sortedPages(){
        return pageGames.stream().sorted(Comparator.comparing(PageGame::getOrderNumber)).collect(Collectors.toList());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Quest getQuest() {
        return quest;
    }

    public void setQuest(Quest quest) {
        this.quest = quest;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public List<PageGame> getPageGames() {
        return pageGames;
    }

    public void setPageGames(List<PageGame> pageGames) {
        this.pageGames = pageGames;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        if(this.start ==null) {
            this.start = start;
        }
    }

    public Date getFinish() {
        return finish;
    }

    public void setFinish(Date finish) {
            this.finish = finish;
    }

    public String getPause() {
        return pause;
    }

    public void setPause(String pause) {
        this.pause = pause;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public Date getPauseStart() {
        return pauseStart;
    }

    public void setPauseStart(Date pauseStart) {
        this.pauseStart = pauseStart;
    }

    public Date getPauseFinish() {
        return pauseFinish;
    }

    public void setPauseFinish(Date pauseFinish) {
        this.pauseFinish = pauseFinish;
    }
}
