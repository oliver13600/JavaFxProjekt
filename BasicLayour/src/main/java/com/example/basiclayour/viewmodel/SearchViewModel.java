package com.example.basiclayour.viewmodel;

import com.example.basiclayour.service.RouteService;
import com.example.basiclayour.service.SearchService;
import com.example.basiclayour.service.TourService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.List;


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

    public void searchSuggestions()
    {
        String keyword = string1.getValue();
        searchService.setKeyword(keyword);
        searchService.findToursByKeyword();
    }

    public List<String> getSuggestions(String keyword){
        searchService.setKeyword(keyword);
        return searchService.findToursByKeyword();
    }

    public String getString1() {
        return string1.get();
    }

    public void setString1(String string1) {
        this.string1.set(string1);
    }





}
