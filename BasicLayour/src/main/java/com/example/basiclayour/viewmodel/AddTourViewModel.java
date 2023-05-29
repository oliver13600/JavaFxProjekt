package com.example.basiclayour.viewmodel;


import com.example.basiclayour.dto.Route;
import com.example.basiclayour.service.RouteService;
import com.example.basiclayour.service.TourService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AddTourViewModel {
    private final StringProperty string1 = new SimpleStringProperty("");
    private final StringProperty string2 = new SimpleStringProperty("");
    private final StringProperty output = new SimpleStringProperty("");
    private final TourService tourService;
    private final RouteService routeService;

    public AddTourViewModel(
            RouteService routeService,
            TourService tourService

    ) {
        this.routeService = routeService;
        this.tourService = tourService;

    }

    public void addTour() {
        String from = string1.get();
        String to = string2.get();

        //String tour = "Tour: " + string1.get() + " " + string2.get();

        Route route = routeService.getRoute(from, to);

        routeService.saveMap(route.getSessionId(), "map.jpg");

        String tourInformation = "From "
                + from + " to "
                + to + " in "
                + route.getFormattedTime().toString()
                + " (" + route.getDistance() + ")";

        System.out.println("Test: " + tourInformation);

        tourService.save(tourInformation);

        output.set(tourInformation);

        string1.set("");
        string2.set("");
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

}
