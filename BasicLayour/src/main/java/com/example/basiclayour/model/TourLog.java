package com.example.basiclayour.model;

import jakarta.persistence.*;
@Entity
public class TourLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String date;
    @Column
    private float time;
    @Column
    private String difficulty; // 1 - 5 stars?
    @Column
    private float totalTime;
    @Column
    private float rating; // 1-100 Rating?

    public TourLog(){

    }

    public TourLog(Long id, String date, float time, String difficulty, float totalTime, float rating) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.difficulty = difficulty;
        this.totalTime = totalTime;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public float getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(float totalTime) {
        this.totalTime = totalTime;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
