package com.example.sweater.entities;

import javax.persistence.*;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String teamName;
    private String capName;
    private String capNumber;
    private String secondCapName;
    private String secondCapNumber;
    private String numberOfPlayers;
    private String code;
    private String dateString;
    private boolean isChecked;
    private boolean isCheckedUser;

    @OneToOne(mappedBy = "team")
    private Game game;
    @ManyToOne
    @JoinColumn
    private Quest quest;

    //new
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<Message> messages;

    public Team() {
    }

    public Team(String teamName, String capName, String capNumber, String secondCapName, String secondCapNumber, String numberOfPlayers, Quest quest) {
        this.teamName = teamName;
        this.capName = capName;
        this.capNumber = capNumber;
        this.secondCapName = secondCapName;
        this.secondCapNumber = secondCapNumber;
        this.numberOfPlayers = numberOfPlayers;
        this.quest = quest;
        this.code = String.format("%040d", new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16)).substring(0,7);
        this.isChecked = true;
        this.isCheckedUser=true;
        String pattern = "dd-MM HH:mm:yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        this.dateString = simpleDateFormat.format(new Date(System.currentTimeMillis()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return id == team.id &&
                Objects.equals(teamName, team.teamName) &&
                Objects.equals(capName, team.capName) &&
                Objects.equals(capNumber, team.capNumber) &&
                Objects.equals(secondCapName, team.secondCapName) &&
                Objects.equals(secondCapNumber, team.secondCapNumber) &&
                Objects.equals(numberOfPlayers, team.numberOfPlayers) &&
                Objects.equals(code, team.code) &&
                Objects.equals(game, team.game) &&
                Objects.equals(quest, team.quest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, teamName, capName, capNumber, secondCapName, secondCapNumber, numberOfPlayers, code, game, quest);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getCapName() {
        return capName;
    }

    public void setCapName(String capName) {
        this.capName = capName;
    }

    public String getCapNumber() {
        return capNumber;
    }

    public void setCapNumber(String capNumber) {
        this.capNumber = capNumber;
    }

    public String getSecondCapName() {
        return secondCapName;
    }

    public void setSecondCapName(String secondCapName) {
        this.secondCapName = secondCapName;
    }

    public String getSecondCapNumber() {
        return secondCapNumber;
    }

    public void setSecondCapNumber(String secondCapNumber) {
        this.secondCapNumber = secondCapNumber;
    }

    public String getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(String numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Quest getQuest() {
        return quest;
    }

    public void setQuest(Quest quest) {
        this.quest = quest;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public boolean isCheckedUser() {
        return isCheckedUser;
    }

    public void setCheckedUser(boolean checkedUser) {
        isCheckedUser = checkedUser;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }
}
