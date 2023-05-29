package com.example.basiclayour.model;

import jakarta.persistence.*;

@Entity
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String name;
    @Column
    private String tourDescription;
    @Column
    private String fromStart;
    @Column
    private String toFinish;
    @Column
    private String transportType; //maybe change to enum
    @Column
    private float tourDistance;
    @Column
    private float estimatedTime;
    @Column
    private String tourInformation;

    public Tour(){

    }
    public Tour(String name, String tourDescription, String fromStart, String toFinish, String transportType, float tourDistance, float estimatedTime, String tourInformation) {
        this.name = name;
        this.tourDescription = tourDescription;
        this.fromStart = fromStart;
        this.toFinish = toFinish;
        this.transportType = transportType;
        this.tourDistance = tourDistance;
        this.estimatedTime = estimatedTime;
        this.tourInformation = tourInformation;
    }

    public Tour(String name){
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTourDescription() {
        return tourDescription;
    }

    public void setTourDescription(String tourDescription) {
        this.tourDescription = tourDescription;
    }

    /*public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }*/

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public float getTourDistance() {
        return tourDistance;
    }

    public void setTourDistance(float tourDistance) {
        this.tourDistance = tourDistance;
    }

    public float getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(float estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public String getTourInformation() {
        return tourInformation;
    }

    public void setTourInformation(String tourInformation) {
        this.tourInformation = tourInformation;
    }
}
