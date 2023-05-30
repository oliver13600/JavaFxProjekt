package com.example.basiclayour.viewmodel;

import com.example.basiclayour.event.Event;
import com.example.basiclayour.event.EventAggregator;
import com.example.basiclayour.service.TourService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TourListViewModel {
    private final ObservableList<String> tours = FXCollections.observableArrayList();

    private final EventAggregator eventAggregator;
    private final TourService tourService;

    public TourListViewModel(
            EventAggregator eventAggregator,
            TourService tourService
    ) {
        this.eventAggregator = eventAggregator;
        this.tourService = tourService;

        tours.addAll(tourService.findAll());

        eventAggregator.addSubscriber(
                Event.NEW_TOUR,
                this::onNewTour
        );
        eventAggregator.addSubscriber(
                Event.DELETE_TOUR,
                this::onDeleteTour
        );
        eventAggregator.addSubscriber(
                Event.SEARCH_TOUR,
                this::onSearchTour
        );
    }

    private void onNewTour() {
        tours.clear();
        tours.addAll(tourService.findAll());
    }

    private void onDeleteTour()
    {
        tours.clear();
        tours.addAll(tourService.findAll());
    }

    private void onSearchTour()
    {
        tours.clear();
        tours.addAll(tourService.findToursByKeyword());
    }


    public ObservableList<String> getTours() {
        return tours;
    }

    public void deleteTour(String keyword){
        tourService.deleteTourByKeyword(keyword);
    }
}
