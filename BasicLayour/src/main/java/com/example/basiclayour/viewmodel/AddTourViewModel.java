package com.example.basiclayour.viewmodel;


import com.example.basiclayour.dto.Route;
import com.example.basiclayour.service.RouteService;
import com.example.basiclayour.service.TourService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AddTourViewModel {

    private final StringProperty tourName = new SimpleStringProperty("");
    private final StringProperty string1 = new SimpleStringProperty("");
    private final StringProperty string2 = new SimpleStringProperty("");
    private final StringProperty output = new SimpleStringProperty("");

    private final ObservableList<String> choiceBoxInputs = FXCollections.observableArrayList();
    private final TourService tourService;
    private final RouteService routeService;

    public AddTourViewModel(
            RouteService routeService,
            TourService tourService

    ) {
        this.routeService = routeService;
        this.tourService = tourService;

    }

    public void addTour(String selectedChoice) {
        boolean allMandatoriesFilledOut = true;
        String tourNameString = tourName.get();
        String from = string1.get();
        String to = string2.get();

        if(tourNameString.isEmpty() || from.isEmpty() || to.isEmpty()){
            allMandatoriesFilledOut = false;
        }

        if(allMandatoriesFilledOut == true){
            String mapfileName = string1.get() + "-to-" + string2.get() + ".jpg";

            Route route = routeService.getRoute(from, to);

            routeService.saveMap(route.getSessionId(), mapfileName);

            String tourInformation = "Tour: " + getSanitizedString(tourNameString)
                    + " From " + getSanitizedString(from)
                    + " to " + getSanitizedString(to)
                    + " in "
                    + route.getFormattedTime().toString()
                    + " (" + route.getDistance() + ")"
                    + " transportation: " + selectedChoice;

            System.out.println(tourInformation);

            tourService.save(tourInformation);

            output.set("Tour: " + getSanitizedString(tourNameString) + " added" + " ~ " + from + " to " + to + " ~");

        } else {
            output.set("Please fill out all TextBoxes");
        }

        string1.set("");
        string2.set("");
        tourName.set("");
    }

    public ObservableList<String> getChoiceBoxInputs(){
        choiceBoxInputs.add(0, "Car");
        choiceBoxInputs.add(1, "Bicycle");
        choiceBoxInputs.add(2, "Walking");
        return choiceBoxInputs;
    }

    public String setDefaultValue(){
        return "Car";
    }

    public String getSanitizedString(String stringToSanitize){
        stringToSanitize = stringToSanitize.replaceAll("\\W+",""); // removes anything that is not a word character
        return stringToSanitize;
    }

    public String getString1() {
        return string1.get();
    }

    public StringProperty string1Property() {
        return string1;
    }

    public void setString1(String string1) {
        this.string1.set(string1);
    }

    public String getString2() {
        return string2.get();
    }

    public StringProperty string2Property() {
        return string2;
    }

    public void setString2(String string2) {
        this.string2.set(string2);
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

    public String getTourName() {
        return tourName.get();
    }

    public StringProperty tourNameProperty() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName.set(tourName);
    }
}
