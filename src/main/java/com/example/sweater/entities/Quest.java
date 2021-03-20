package com.example.sweater.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Quest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private String shortDescription;

    private String description;

    private String filename;

    private String filenameSmall;

    private String place;

    @OneToMany(mappedBy = "quest", cascade = CascadeType.ALL)
    private List<Page> pages;

    @OneToMany(mappedBy = "quest", cascade = CascadeType.ALL)
    private Set<Team> teams;

    @OneToMany(mappedBy = "quest", cascade = CascadeType.ALL)
    private Set<Game> games;
    @OneToMany(mappedBy = "quest", cascade = CascadeType.ALL)
    private List<Element> finalPageElements;


    public Quest() {
    }

    public Quest(String place, String name, String shortDescription, String description, String filename, String filenameSmall) {
        this.place = place;
        this.filename = filename;
        this.name = name;
        this.shortDescription = shortDescription;
        this.description = description;
        this.filenameSmall = filenameSmall;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public List<Page> getPages() {
        return pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    public String getFilenameSmall() {
        return filenameSmall;
    }

    public void setFilenameSmall(String filenameSmall) {
        this.filenameSmall = filenameSmall;
    }

    public List<Element> getFinalPageElements() {
        return finalPageElements;
    }

    public void setFinalPageElements(List<Element> finalPageElements) {
        this.finalPageElements = finalPageElements;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
