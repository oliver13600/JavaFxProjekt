package com.example.basiclayour.viewmodel;

import com.example.basiclayour.service.RouteService;
import com.example.basiclayour.service.SearchService;
import com.example.basiclayour.service.TourService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SearchViewModel {
    private final StringProperty string1 = new SimpleStringProperty("");

    public StringProperty string1Property(){return string1;}

    private final TourService tourService;

    private final SearchService searchService;


    public SearchViewModel(
            TourService tourService,
            SearchService searchService
    ) {
        this.tourService = tourService;
        this.searchService = searchService;

    }

    public void searchTour()
    {
        String keyword = string1.getValue();
        searchService.setKeyword(keyword);
        searchService.searchTours();
    }





}
