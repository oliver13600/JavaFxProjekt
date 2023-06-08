package com.example.basiclayour.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class TourLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private LocalDateTime dateTime;
    @Column
    private String comment;
    @Column
    private Integer difficulty; // 1 - 5 // 1=Beginner // 2=Easy // 3=Medium // 4=Hard // 5=Extreme
    @Column
    private float totalTime;
    @Column
    private Integer rating; // 1 - 5 stars

    @ManyToOne
    @JoinColumn(name = "tour_name")
    private Tour tour;

    public TourLog(){

    }

    public TourLog(LocalDateTime dateTime, String comment, Integer difficulty, float totalTime, int rating, Tour tour) {
        this.dateTime = dateTime;
        this.comment = comment;
        this.difficulty = difficulty;
        this.totalTime = totalTime;
        this.rating = rating;
        this.tour = tour;
    }

    public TourLog(LocalDateTime dateTime, String comment, Integer difficulty, float totalTime, int rating) {
        this.dateTime = dateTime;
        this.comment = comment;
        this.difficulty = difficulty;
        this.totalTime = totalTime;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public float getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(float totalTime) {
        this.totalTime = totalTime;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public String getTourLogInformation() {
        return this.dateTime.toString() + this.comment + "|" + this.difficulty.toString()
                + "|" +this.totalTime + "|" + this.rating.toString() + "|" + this.tour;
    }
}
