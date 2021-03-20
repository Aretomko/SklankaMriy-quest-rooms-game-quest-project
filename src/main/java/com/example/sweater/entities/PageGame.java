package com.example.sweater.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
public class PageGame implements Comparable<PageGame> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "pageGame", cascade = CascadeType.ALL)
    private Set<Element> elements;

    @OneToMany(mappedBy = "pageGame", cascade = CascadeType.ALL)
    private Set<Hint> hints;

    @OneToMany(mappedBy = "pageGame", cascade = CascadeType.ALL)
    private Set<Answer> answers;

    private String orderNumber;

    private String orderNumberReal;

    private String name;

    private boolean answered;

    private double time;

    private double timeElapsed;

    private double answerTime;

    private String foundAnswers;

    private Date start;

    @ManyToOne
    @JoinColumn
    private Game game;

    public PageGame(){
    }

    public PageGame(String number , String realNumber , Game game, double time, String name) {
        this.orderNumber = number;
        this.orderNumberReal = realNumber;
        this.game = game;
        this.answered = false;
        this.time  = time;
        this.name = name;
        this.timeElapsed = 0.00;
        this.foundAnswers = "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PageGame pageGame = (PageGame) o;
        return answered == pageGame.answered &&
                Objects.equals(id, pageGame.id) &&
                Objects.equals(elements, pageGame.elements) &&
                Objects.equals(hints, pageGame.hints) &&
                Objects.equals(answers, pageGame.answers) &&
                Objects.equals(orderNumber, pageGame.orderNumber) &&
                Objects.equals(game, pageGame.game);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, elements, hints, answers, orderNumber, answered, game);
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Set<Element> getElements() {
        return elements;
    }

    public void setElements(Set<Element> elements) {
        this.elements = elements;
    }

    public Set<Hint> getHints() {
        return hints;
    }

    public void setHints(Set<Hint> hints) {
        this.hints = hints;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(double answerTime) {
        this.answerTime = answerTime;
    }

    public double getTimeElapsed() {
        return timeElapsed;
    }

    public void setTimeElapsed(double timeElapsed) {
        this.timeElapsed = timeElapsed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFoundAnswers() {
        return foundAnswers;
    }

    public void setFoundAnswers(String foundAnswers) {
        this.foundAnswers = foundAnswers;
    }

    public String getOrderNumberReal() {
        return orderNumberReal;
    }

    public void setOrderNumberReal(String orderNumberReal) {
        this.orderNumberReal = orderNumberReal;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    @Override
    public int compareTo(PageGame o) {
        return Integer.valueOf(this.getOrderNumberReal()).compareTo(Integer.valueOf(o.getOrderNumberReal()));
    }
}
