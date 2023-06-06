package com.example.basiclayour.viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public class AddTourLogViewModel {

    private final StringProperty minutes = new SimpleStringProperty("");
    private final StringProperty hours = new SimpleStringProperty("");

    private final ObjectProperty<LocalDate> logDate = new SimpleObjectProperty<>();
    private final StringProperty logComment = new SimpleStringProperty("");
    private final StringProperty logDifficulty = new SimpleStringProperty("");
    private final StringProperty totalTime = new SimpleStringProperty("");
    private final StringProperty output = new SimpleStringProperty("");
    private final ObservableList<String> choiceBoxRating = FXCollections.observableArrayList();

    public void addTourLog(String selectedRating){
        boolean allMandatoriesFilledOut = true;

        String hourString = hours.get();
        String minuteString = minutes.get();

        String logCommentString = logComment.get();
        String logDifficultyString = logDifficulty.get();
        String totalTimeString = totalTime.get();

        if(hourString.isEmpty() || minuteString.isEmpty() || logCommentString.isEmpty() || logDifficultyString.isEmpty() || totalTimeString.isEmpty() || logDate.getValue() == null){
            allMandatoriesFilledOut = false;
        }

        if(allMandatoriesFilledOut == true){
            String outputString = "Comment: " + logCommentString + " Difficulty: "+ logDifficultyString + " TotalTime: " + totalTimeString;
            System.out.println(" Comment: " + logCommentString + " Difficulty: "+ logDifficultyString + " TotalTime: " + totalTimeString);
            System.out.println(" Hours: "+ hourString + " Minutes: " + minuteString + " Rating: " + selectedRating);
            System.out.println("Date: " + logDate);

            output.set(outputString);
        } else {
            output.set("Please fill out all TextFields and pick a date");
        }

        hours.set("");
        minutes.set("");
        logComment.set("");
        logDifficulty.set("");
        totalTime.set("");
        logDate.setValue(null);
    }

    public ObservableList<String> getRatingChoiceBoxInput(){
        choiceBoxRating.add(0, "1 ★");
        choiceBoxRating.add(1, "2 ★★");
        choiceBoxRating.add(2, "3 ★★★");
        choiceBoxRating.add(3, "4 ★★★★");
        choiceBoxRating.add(4, "5 ★★★★★");

        return choiceBoxRating;
    }
    public String setRatingChoiceBoxInput(){
        return "5 ★★★★★";
    }

    public LocalDate getLogDate() {
        return logDate.get();
    }

    public ObjectProperty<LocalDate> logDateProperty() {
        return logDate;
    }

    public void setLogDate(LocalDate logDate) {
        this.logDate.set(logDate);
    }

    public String getLogComment() {
        return logComment.get();
    }

    public StringProperty logCommentProperty() {
        return logComment;
    }

    public void setLogComment(String logComment) {
        this.logComment.set(logComment);
    }

    public String getLogDifficulty() {
        return logDifficulty.get();
    }

    public StringProperty logDifficultyProperty() {
        return logDifficulty;
    }

    public void setLogDifficulty(String logDifficulty) {
        this.logDifficulty.set(logDifficulty);
    }

    public String getTotalTime() {
        return totalTime.get();
    }

    public StringProperty totalTimeProperty() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime.set(totalTime);
    }

    public String getOutput() {
        return output.get();
    }

    public StringProperty outputProperty() {
        return output;
    }

    public void setOutput(String output) {
        this.output.set(output);
    }

    public ObservableList<String> getChoiceBoxRating() {
        return choiceBoxRating;
    }

    public String getMinutes() {
        return minutes.get();
    }

    public StringProperty minutesProperty() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes.set(minutes);
    }

    public String getHours() {
        return hours.get();
    }

    public StringProperty hoursProperty() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours.set(hours);
    }
}
