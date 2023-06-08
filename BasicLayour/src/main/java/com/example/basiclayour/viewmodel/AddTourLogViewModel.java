package com.example.basiclayour.viewmodel;

import com.example.basiclayour.service.MapService;
import com.example.basiclayour.service.TourLogService;
import com.example.basiclayour.service.TourService;
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
    private final StringProperty totalTime = new SimpleStringProperty("");
    private final StringProperty output = new SimpleStringProperty("");
    private final ObservableList<String> choiceBoxRating = FXCollections.observableArrayList();

    private final ObservableList<String> choiceBoxDifficulty = FXCollections.observableArrayList();

    private final TourLogService tourLogService;

    private final MapService mapService;

    public AddTourLogViewModel(TourLogService tourLogService, MapService mapService){
        this.tourLogService = tourLogService;
        this.mapService = mapService;
    }

    public void addTourLog(String selectedRating, String selectedDifficulty){
        boolean allMandatoriesFilledOut = true;
        boolean totalTimeValid = true;
        boolean timeIsValid = true;
        boolean hoursValid = true;
        boolean minutesValid = true;
        boolean selectedTourIsTrue = true;

        String hourString = hours.get();
        String minuteString = minutes.get();

        String logCommentString = logComment.get();
        String totalTimeString = totalTime.get();

        String outPutString = new String();

        String selectedTour = mapService.getTmpSelectedItem();

        if(checkTotalTimeIsValid(getSanitizedTotalTime(totalTimeString)) == false){
            totalTimeValid = false;
        }

        if(selectedTour.isEmpty()){
            selectedTourIsTrue = false;
        }

        if(checkTotalTimeIsValid(getSanitizedTotalTime(totalTimeString))){
            String totalTimeStringSplit[] = totalTimeString.split(":");
            if(checkTotalTimeHour(totalTimeStringSplit[0])  == false || checkMinutesIsValid(totalTimeStringSplit[1]) == false || checkMinutesIsValid(totalTimeStringSplit[2]) == false){
                timeIsValid = false;
            }
        }

        if(checkHoursIsValid(getSanitizedTime(hourString)) == false){
            hoursValid = false;
        }

        if(checkMinutesIsValid(getSanitizedTime(minuteString)) == false){
            minutesValid = false;
        }

        if(getSanitizedTime(hourString).isEmpty() || getSanitizedTime(minuteString).isEmpty()  || getSanitizedComment(logCommentString).isEmpty() || selectedDifficulty.isEmpty() || getSanitizedTotalTime(totalTimeString).isEmpty() || logDate.getValue() == null){
           allMandatoriesFilledOut = false;
        }

        if(allMandatoriesFilledOut == true && totalTimeValid == true && hoursValid == true && minutesValid == true && timeIsValid == true && selectedTourIsTrue == true){
            String tourLogInformation = "Date; " + logDate + " Hours;" + getSanitizedTime(hourString) + " Minutes;" + getSanitizedTime(minuteString)
                    + " Comment;" + getSanitizedComment(logCommentString)
                    + "|Difficulty;" + selectedDifficulty
                    + " TotalTime;" + getSanitizedTotalTime(totalTimeString)
                    + "|Rating;" + selectedRating;

            outPutString += tourLogInformation;

            tourLogService.save(tourLogInformation, selectedTour);

        }
        if (allMandatoriesFilledOut == false) {
            outPutString += "Please fill out all TextFields and pick a date\n";
        }

        if (totalTimeValid == false){
            outPutString += "Total Time is formatted in {hh:mm:ss}\n";
        }
        if (timeIsValid == false){
            outPutString += "{hh} go from 0-23 and {mm/ss} go from 0-59\n";
        }
        if (hoursValid == false){
            outPutString += "Hours go from 0-23\n";
        }
        if (minutesValid == false){
            outPutString += "Minutes go from 0-59\n";
        }
        if(selectedTourIsTrue == false){
            outPutString += "select a TOUR to write the log to";
        }

        output.set(outPutString);

        hours.set("");
        minutes.set("");
        logComment.set("");
        totalTime.set("");
        logDate.setValue(null);
    }

    public boolean checkTotalTimeIsValid(String totalTimeValid){
        if(totalTimeValid.matches("^(\\d{2}:\\d{2}:\\d{2}$)")){ // checks hh:mm:ss pattern
            return true;
        }
        return false;
    }

    public boolean checkTotalTimeHour(String totalTimeHour){ // if your tour is longer than 24 hour => ultra marathon
        if(totalTimeHour.matches("^(?:[0-9]|[1-9][0-9]|100)$")){ // checks 0-9 // first digit 1-9 // second digit 0-9 // 100
            return true;
        }
        return false;
    }

    public boolean checkHoursIsValid(String hours){
        if(hours.matches("^(?:[0-9]|1[0-9]|2[0-3])$")){ // checks if 0-9 // 10 19 // 20 - 23
            return true;
        }
        return false;
    }

    public boolean checkMinutesIsValid(String minutes){
        if(minutes.matches("^(?:[0-9]|[1-5][0-9])$")){ // checks if 0-9 // 1-5 first digit // 0-9 second digit
            return true;
        }
        return false;
    }

    public String getSanitizedTime(String stringToSanitize){
        stringToSanitize = stringToSanitize.replaceAll("[^0-9]",""); // removes everything except numbers
        return stringToSanitize;
    }

    public String getSanitizedTotalTime(String stringToSanitize){
        stringToSanitize = stringToSanitize.replaceAll("[^0-9:]",""); // removes everything except numbers and ":"
        return stringToSanitize;
    }

    public String getSanitizedComment(String stringToSanitize){
        stringToSanitize = stringToSanitize.replaceAll("[^a-zA-Z0-9äöüÄÖÜß]",""); // removes everything except word characters + numbers + umlauts
        return stringToSanitize;
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

    public ObservableList<String> getDifficultyChoiceBoxInput(){
        choiceBoxDifficulty.add(0, "Beginner");
        choiceBoxDifficulty.add(1, "Easy");
        choiceBoxDifficulty.add(2, "Normal");
        choiceBoxDifficulty.add(3, "Hard");
        choiceBoxDifficulty.add(4, "Extreme");

        return choiceBoxDifficulty;
    }

    public String setDifficultyChoiceBoxInput(){
        return "Beginner";
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

    public ObservableList<String> getChoiceBoxDifficulty() {
        return choiceBoxDifficulty;
    }
}
