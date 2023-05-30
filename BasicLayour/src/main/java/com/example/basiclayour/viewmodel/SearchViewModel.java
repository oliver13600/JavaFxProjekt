package com.example.basiclayour.viewmodel;

import com.example.basiclayour.service.RouteService;
import com.example.basiclayour.service.TourService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SearchViewModel {
    private final StringProperty string1 = new SimpleStringProperty("");

    public StringProperty string1Property(){return string1;}

    private final TourService tourService;

    private final RouteService routeService;

    public SearchViewModel(
            RouteService routeService,
            TourService tourService
    ) {
        this.routeService = routeService;
        this.tourService = tourService;

    }

    public void searchTour()
    {
        String keyword = string1.toString();
        tourService.findToursByKeyword();
    }





}
