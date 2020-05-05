package com.example.sweater.domain;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Page {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "page", cascade = CascadeType.ALL)
    private Set<Element> elements;
    @OneToMany(mappedBy = "page", cascade = CascadeType.ALL)
    private Set<Hint> hints;
    @OneToMany(mappedBy = "page", cascade = CascadeType.ALL)
    private Set<Answer> answers;

    private String name;

    private int orderNumber;

    private double time;

    @ManyToOne
    @JoinColumn
    private Quest quest;

    public Page() {
    }

    public Page(int number , Quest quest, double time, String name) {
        this.orderNumber = number;
        this.quest = quest;
        this.time = time;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Page page = (Page) o;
        return orderNumber == page.orderNumber &&
                Double.compare(page.time, time) == 0 &&
                id.equals(page.id) &&
                Objects.equals(elements, page.elements) &&
                Objects.equals(hints, page.hints) &&
                Objects.equals(answers, page.answers) &&
                Objects.equals(name, page.name) &&
                Objects.equals(quest, page.quest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }

    public Quest getQuest() {
        return quest;
    }

    public void setQuest(Quest quest) {
        this.quest = quest;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
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

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
