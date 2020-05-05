package com.example.sweater.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Element {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private boolean isText;
    private boolean isVideo;
    private boolean isImage;

    private String string;

    private String fileName;

    private int orderNumber;

    @ManyToOne
    @JoinColumn
    private Page page;
    @ManyToOne
    @JoinColumn
    private Quest quest;
    @ManyToOne
    @JoinColumn
    private PageGame pageGame;

    public Element() {
    }

    public Element(boolean isText, boolean isVideo, boolean isImage, String inputString, int number, Page page) {
        this.page = page;
        this.orderNumber = number;
        if (isText && !isVideo && !isImage){
            this.string = inputString;
            this.isText = true;
            this.isVideo = false;
            this.isImage = false;
        }
        if (isImage && !isText && !isVideo){
            this.fileName = inputString;
            this.isText =  false;
            this.isVideo = false;
            this.isImage = true;
        }
        if (isVideo && !isImage && !isText){
            this.fileName = inputString;
            this.isText =  false;
            this.isVideo = true;
            this.isImage = false;
        }
    }
    public Element(boolean isText, boolean isVideo, boolean isImage, String inputString, int number, PageGame pageGame) {
        this.pageGame = pageGame;
        this.orderNumber = number;
        if (isText && !isVideo && !isImage){
            this.string = inputString;
            this.isText = true;
            this.isVideo = false;
            this.isImage = false;
        }
        if (isImage && !isText && !isVideo){
            this.fileName = inputString;
            this.isText =  false;
            this.isVideo = false;
            this.isImage = true;
        }
        if (isVideo && !isImage && !isText){
            this.fileName = inputString;
            this.isText =  false;
            this.isVideo = true;
            this.isImage = false;
        }

    }
    public Element(boolean isText, boolean isVideo, boolean isImage, String inputString, int number, Quest quest) {
        this.quest = quest;
        this.orderNumber = number;
        if (isText && !isVideo && !isImage){
            this.string = inputString;
            this.isText = true;
            this.isVideo = false;
            this.isImage = false;
        }
        if (isImage && !isText && !isVideo){
            this.fileName = inputString;
            this.isText =  false;
            this.isVideo = false;
            this.isImage = true;
        }
        if (isVideo && !isImage && !isText){
            this.fileName = inputString;
            this.isText =  false;
            this.isVideo = true;
            this.isImage = false;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Element element = (Element) o;
        return orderNumber == element.orderNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderNumber);
    }

    public static void increaseOrderNumber(Element i){
        i.setOrderNumber(i.orderNumber+1);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isText() {
        return isText;
    }

    public void setText(boolean text) {
        isText = text;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public boolean isVideo() {
        return isVideo;
    }

    public void setVideo(boolean video) {
        isVideo = video;
    }

    public boolean isImage() {
        return isImage;
    }

    public void setImage(boolean image) {
        isImage = image;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public PageGame getPageGame() {
        return pageGame;
    }

    public void setPageGame(PageGame pageGame) {
        this.pageGame = pageGame;
    }

    public Quest getQuest() {
        return quest;
    }

    public void setQuest(Quest quest) {
        this.quest = quest;
    }

}
